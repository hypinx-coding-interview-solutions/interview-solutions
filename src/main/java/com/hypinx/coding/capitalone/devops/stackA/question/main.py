#!/usr/bin/env python3

# This script is used to initialize the directories with all the relevant files used in the interview
# Execute this once and all the files will be populated with the raw code.

import os

# Define the prompt
prompt_contents = """# Risk Rating Application

## Application Overview

This application performs analysis on files that are dropped into an S3 bucket by an upstream process and outputs a risk rating for each
customer seeking a loan, which is available to internal employees via a User Interface (UI).  The application is containerized, runs in 
the Amazon Elastic Container Service and is only accessible to internal corporate users.  The application consists of two containers:

    1. risk-rating-application-processor
       - Processes data provided by an upstream application to generate risk rating scores for potential and current customers by downloading
       files from S3 and executing proprietary business logic, then making the resulting scores available via the UI 

    2. risk-rating-application-ui
       - Provides a user interface for internal associates to view credit risk rating data for potential and current customers.  This UI
       is read-only, it does not provide the ability to edit risk rating data.

Access to the UI flows through an Application Load Balancer (ALB) where SSL is terminated.

## What Works

1. All of the CloudFormation templates in the cfn folder are syntactically correct.  They all validate correctly with the make validate command.

2. The IAM policies from the iam folder are all syntactically correct

3. The ./cfn/orchestrator.yml file is configured to bring up the other CloudFormation templates in the correct order.

4. There are no spelling errors.

## AWS Account & Corporate Network Information

These AWS accounts are multi-tenant, meaning multiple applications owned by different teams operate simultaneously in these accounts.
Access to AWS account resources owned by other teams is strictly monitored and audited through an internal tool.

| AWS Account Number | Description   | Active Regions | VPC Network Range |
| ------------------ | ------------- | -------------- | ----------------- |
| 888541340307       | Nonproduction | us-east-2      | 172.17.0.0/24     |
| 129049408267       | Production    | us-east-2      | 172.28.0.0/24     |
| 129049408267       | Production    | us-west-2      | 172.29.0.0/24     |

This application is for internal users only.  The internal network range is 172.8.0.0/16.

## Repository Structure

| Folder     | Description                                                                                                                      |
| ---------- | -------------------------------------------------------------------------------------------------------------------------------- |
| cfn        | Contains CloudFormation files for creating AWS infrastructure                                                                    |
| iam        | Contains Identity and Access Management files permitting the application and infrastructure to access the required AWS APIs      |
| ./Makefile | Contains deployment/update/destroy commands. Execute locally with make upload-cloudformation or make deploy-stack-production |

## CloudFormation

The CloudFormation templates in this project follow the nested stack pattern, where the orchestrator.yml is responsible for launching the rest
of the templates in the appropriate order.

| Template File                                       | Description                                                                        |
| --------------------------------------------------- | ---------------------------------------------------------------------------------- |
| orchestrator.yml                                  | Orchestrates the creation of all necessary infrastructure in the appropriate order |
| risk-rating-application-iam.yml                   | Creates necessary application and infrastructure permissions policies              |
| risk-rating-application-load-balancer.yml         | Creates necessary application load balancer infrastructure resources               |
| risk-rating-cloudwatch-logs.yml                   | Creates necessary CloudWatch log infrastructure                                    |
| risk-rating-elastic-container-service-cluster.yml | Creates necessary container cluster infrastructure                                 |
| risk-rating-security-groups.yml                   | Creates necessary security groups for configuring network ingress/egress           |
"""

# Define the Makefile
makefile_contents = """# Directory containing CloudFormation templates
TEMPLATE_DIR := ./cfn

# Llist of CloudFormation templates to validate
TEMPLATE_FILES := $(wildcard $(TEMPLATE_DIR)/*.yml)

# Since the template validation does not produce files, we use .PHONY
.PHONY: validate

validate: $(TEMPLATE_FILES)
	@echo "Validating CloudFormation templates..."
	@for template in $^; do \
		aws cloudformation validate-template --output table --no-cli-pager --template-body "file://$$template"; \
		if [ $$? -eq 0 ]; then \
			echo "Validation successful for $$template"; \
		else \
			echo "Validation failed for $$template"; \
			exit 1; \
		fi; \
	done

check_delete:
	@echo -n "Are you sure? [y/N] " && read ans && [ $${ans:-N} = y ]

# Corrected deploy-stack-nonproduction and deploy-stack-production targets
deploy-stack-nonproduction: validate
    aws cloudformation create-stack \
        --stack-name risk-rating-application-nonproduction-stack \
        --template-body s3://risk-rating-infrastructure-s3-bucket-nonproduction/cfn/orchestrator.yml \
        --parameters \
            ParameterKey=DesiredCapacity,ParameterValue=1 \
            ParameterKey=InstanceType,ParameterValue=t2.micro \
            ParameterKey=MaxSize,ParameterValue=4 \
            ParameterKey=KeyName,ParameterValue=risk-rating-nonprod-key \
            ParameterKey=SubnetId,ParameterValue=subnet-1a2b3c4d5e6f78901 \
            ParameterKey=VpcId,ParameterValue=vpc-1a2b3c4d5e6f78901

deploy-stack-production: validate
    aws cloudformation create-stack \
        --stack-name risk-rating-application-production-stack \
        --template-body s3://risk-rating-infrastructure-s3-bucket-production/cfn/orchestrator.yml \
        --parameters \
            ParameterKey=DesiredCapacity,ParameterValue=4 \
            ParameterKey=InstanceType,ParameterValue=c5.4xlarge \
            ParameterKey=MaxSize,ParameterValue=4 \
            ParameterKey=KeyName,ParameterValue=risk-rating-prod-key \
            ParameterKey=SubnetId,ParameterValue=subnet-0a1b2c3d4e5f67890 \
            ParameterKey=VpcId,ParameterValue=vpc-0a1b2c3d4e5f67890


upload-cloudformation-nonproduction: validate
	aws s3 cp ./cfn/* s3://risk-rating-infrastructure-s3-bucket-nonproduction/cfn/ --recursive

upload-cloudformation-production: validate
	aws s3 cp ./cfn/* s3://risk-rating-infrastructure-s3-bucket-production/cfn/ --recursive
"""

# Define JSON files in the 'iam' folder
risk_rating_s3_access_nonproduction_contents = """{
    "Version": "2012-10-17",
    "Id": "risk-rating-application-processor-role-nonproduction",
    "Statement": [
        {
            "Effect": "Allow",
            "Action": [
                "s3:GetObject",
                "s3:PutObject",
                "s3:DeleteObject",
                "s3:ListObject"
            ],
            "Resource": [
                "arn:aws:s3:::risk-rating-data-bucket-us-east-2-nonproduction/*",
                "arn:aws:s3:::risk-rating-data-bucket-us-east-2-production/*",
                "arn:aws:s3:::karthik-test-performance-data/*"
            ]
        },
        {
            "Effect": "Allow",
            "Action": [
                "s3:ListAllMyBuckets"
            ],
            "Resource": [
                "arn:aws:s3:::*"
            ]
        }
    ]
}
"""

risk_rating_s3_access_production_contents = """{
    "Version": "2012-10-17",
    "Id": "risk-rating-application-processor-role-production",
    "Statement": [
        {
            "Effect": "Allow",
            "Action": [
                "s3:GetObject",
                "s3:PutObject",
                "s3:DeleteObject",
                "s3:ListObject"
            ],
            "Resource": [
                "arn:aws:s3:::risk-rating-data-bucket-us-east-2-production/*"
            ]
        },
        {
            "Effect": "Allow",
            "Action": [
                "s3:ListAllMyBuckets"
            ],
            "Resource": [
                "arn:aws:s3:::*"
            ]
        }
    ]
}
"""

# Define YAML files in the 'cfn' folder
orchestrator_contents = """# This CloudFormation template is the \"orchestrator\" template that creates all of the nested/child stacks
# that make up the ECS-based Risk Rating application.  This is the single template used by the CI/CD pipeline
# to deploy the entire infrastructure stack.  It brings up all the child stacks (ECS cluster, Load Balancer, IAM 
# policies, CloudWatch log resources, and security groups) in the appropriate order and is capable of tearing them
# all down as one deployable unit.

AWSTemplateFormatVersion: \'2010-09-09\'
Parameters:
  DesiredCapacity:
    Type: Number
    Default: \'1\'
    Description: Number of instances to launch in your ECS cluster. 

  InstanceType:
    Description: EC2 instance type
    Type: String
    Default: t2.micro
    AllowedValues:
      - t2.micro
      - t2.small
      - t2.medium
      - t2.large
      - m5.large
      - m5.xlarge
      - m5.2xlarge
      - m5.4xlarge
      - m5.10xlarge
      - c5.large
      - c5.xlarge
      - c5.2xlarge
      - c5.4xlarge
      - c5.8xlarge
    ConstraintDescription: Please choose a valid instance type.

  KeyName:
    Type: \'AWS::EC2::KeyPair::KeyName\'
    Description: Name of an existing EC2 KeyPair to enable SSH access to the ECS instances.

  MaxSize:
    Type: Number
    Default: \'4\'
    Description: Maximum number of instances that can be launched in your ECS cluster.

  SubnetId:
    Type: \'List<AWS::EC2::Subnet::Id>\'
    Description: Select at least two subnets in your selected VPC.  

  VpcId:
    Type: \'AWS::EC2::VPC::Id\'
    Description: Select a VPC that allows instances to access the Internet.

Resources:
  ECSCluster:
    Type: AWS::CloudFormation::Stack
    DependsOn: RiskRatingApplicationLoadBalancer
    Properties:
      Parameters:
        AutoscalingRoleArn:
          Fn::GetAtt:
          - RiskRatingCloudWatchLogs
          - Outputs.CloudWatchLogsGroupName
        DesiredCapacity: !Ref DesiredCapacity
        EC2InstanceProfile:
          Fn::GetAtt:
          - RiskRatingApplicationIAM
          - Outputs.EC2InstanceProfile
        ECSSecurityGroup:
          Fn::GetAtt:
          - RiskRatingSecurityGroups
          - Outputs.ECSSecurityGroup
        ECSTargetGroupArn:
          Fn::GetAtt:
          - RiskRatingApplicationLoadBalancer
          - Outputs.ECSTargetGroupArn
        InstanceType: !Ref InstanceType
        KeyName: !Ref KeyName
        LoadBalancerFullName:
          Fn::GetAtt:
          - RiskRatingApplicationLoadBalancer
          - Outputs.LoadBalancerFullName
        MaxSize: !Ref MaxSize
        SubnetId: !Ref SubnetId
        VpcId: !Ref VpcId
      TemplateURL: https://s3.amazonaws.com/risk-rating-infrastructure-s3-bucket-nonproduction/elastic-container-service-cluster.yml
      TimeoutInMinutes: \'25\'

  RiskRatingApplicationLoadBalancer:
    Type: AWS::CloudFormation::Stack
    DependsOn: RiskRatingApplicationIAM
    Properties:
      Parameters:
        ECSLoadBalancerSecurityGroup:
          Fn::GetAtt:
          - RiskRatingSecurityGroups
          - Outputs.ECSLoadBalancerSecurityGroup
        ECSSecurityGroup:
          Fn::GetAtt:
          - RiskRatingSecurityGroups
          - Outputs.ECSSecurityGroup
        SubnetId: !Ref SubnetId
        VpcId: !Ref VpcId
      TemplateURL: https://s3.amazonaws.com/risk-rating-infrastructure-s3-bucket-nonproduction/risk-rating-application-load-balancer.yml
      TimeoutInMinutes: \'10\'

  RiskRatingApplicationIAM:
    Type: AWS::CloudFormation::Stack
    Properties:
      Capabilities: CAPABILITY_IAM
      TemplateURL: https://s3.amazonaws.com/risk-rating-infrastructure-s3-bucket-nonproduction/risk-rating-application-iam.yml
      TimeoutInMinutes: \'10\'

  RiskRatingCloudWatchLogs:
    Type: AWS::CloudFormation::Stack
    Properties:
      TemplateURL: https://s3.amazonaws.com/risk-rating-infrastructure-s3-bucket-nonproduction/risk-rating-cloudwatch-logs.yml
      TimeoutInMinutes: \'10\'

  RiskRatingSecurityGroups:
    Type: AWS::CloudFormation::Stack
    Properties:
      TemplateURL: https://s3.amazonaws.com/risk-rating-infrastructure-s3-bucket-nonproduction/risk-rating-security-groups.yml
      TimeoutInMinutes: \'10\'
"""

risk_rating_application_iam_contents = """# This CloudFormation template is responsible for creating all Identity and Access Management (IAM)
# resources for the Risk Rating infrastructure stack. IAM allows users to define permissions assigned
# to user accounts, EC2 virtual machine instances, ECS container instances, and other infrastructure
# in AWS for calling other AWS APIs and can be used to tightly control which actions 
# applications, users, and infrastructure can take.

AWSTemplateFormatVersion: \'2010-09-09\'

Resources:
  AutoscalingRole:
    Type: \'AWS::IAM::Role\'
    Properties:
      AssumeRolePolicyDocument:
        Statement:
          - Effect: Allow
            Principal:
              Service:
                - application-autoscaling.amazonaws.com
            Action:
              - \'sts:AssumeRole\'
      Path: /
      Policies:
        - PolicyName: service-autoscaling
          PolicyDocument: 
            Statement:
              - Effect: Allow
                Action:
                  - \'application-autoscaling:*\'
                  - \'cloudwatch:DescribeAlarms\'
                  - \'cloudwatch:PutMetricAlarm\'
                  - \'ecs:DescribeServices\'
                  - \'ecs:UpdateService\'
                Resource: \'*\'

  EC2InstanceProfile:
    Type: \'AWS::IAM::InstanceProfile\'
    Properties:
      Path: /
      Roles:
        - !Ref EC2Role

  EC2Role:
    Type: \'AWS::IAM::Role\'
    Properties:
      AssumeRolePolicyDocument:
        Statement:
          - Effect: Allow
            Principal:
              Service:
                - ec2.amazonaws.com
            Action:
              - \'sts:AssumeRole\'
      Path: /
      Policies:
        - PolicyName: ecs-service
          PolicyDocument: 
            Statement: 
              - Effect: Allow
                Action:
                  - \'ecs:CreateCluster\'
                  - \'ecs:DeregisterContainerInstance\'
                  - \'ecs:DiscoverPollEndpoint\'
                  - \'ecs:Poll\'
                  - \'ecs:RegisterContainerInstance\'
                  - \'ecs:StartTelemetrySession\'
                  - \'ecs:Submit*\'
                  - \'logs:CreateLogStream\'
                  - \'logs:PutLogEvents\'
                Resource: \'*\'

  RiskRatingApplicationProcessorRole:
    Type: \'AWS::IAM::Role\'
    Properties:
      AssumeRolePolicyDocument:
        Statement:
          - Effect: Allow
            Principal:
              Service:
                - ecs.amazonaws.com
              Action:
                - \'sts:AssumeRole\'
      Path: /
      Policies:
        - PolicyName: risk-rating-application-processor-role
          PolicyDocument:
            Statement:
              - Effect: Allow
                Action:
                  - \'elasticloadbalancing:DeregisterInstancesFromLoadBalancer\'
                  - \'elasticloadbalancing:DeregisterTargets\'
                  - \'elasticloadbalancing:Describe*\'
                  - \'elasticloadbalancing:RegisterInstancesWithLoadBalancer\'
                  - \'elasticloadbalancing:RegisterTargets\'
                  - \'ec2:Describe*\'
                  - \'ec2:AuthorizeSecurityGroupIngress\'
                Resource: \'*\'

Outputs:
  AutoscalingRoleArn:
    Description: Autoscaling Role for cluster
    Value: !Ref AutoscalingRole

  EC2InstanceProfile:
    Description: EC2 Instance Profile Name
    Value: !Ref EC2InstanceProfile

  EC2Role:
    Description: EC2 Role Name
    Value: !Ref EC2Role

  RiskRatingApplicationProcessorRole:
    Description: Risk Rating Application Processor Role
    Value: !Ref RiskRatingApplicationProcessorRole
"""

risk_rating_application_load_balancer_contents = """# This template creates necessary infrastructure to load balance
# traffic to the Risk Rating application.

AWSTemplateFormatVersion: 2010-09-09

Mappings:
  AWSRegionToCertificate:
    us-east-1:
      CertificateArn: arn:aws:acm:us-east-1:${AWS::AccountId}:certificate/12345678-3215-1234-8875-123456789012
    us-east-2:
      CertificateArn: arn:aws:acm:us-east-2:${AWS::AccountId}:certificate/12345678-1234-4321-0987-098765432122
    us-west-1:
      CertificateArn: arn:aws:acm:us-west-1:${AWS::AccountId}:certificate/65748339-7643-6594-1234-337256319532
    us-west-2:
      CertificateArn: arn:aws:acm:us-west-2:${AWS::AccountId}:certificate/87300421-1356-3223-1531-829663682352

Parameters:
  ECSLoadBalancerSecurityGroup:
    Type: \'AWS::EC2::SecurityGroup\'
    Description: Select a Load Balancer Security Group that allows traffic inbound from the corporate network.

  ECSSecurityGroup:
    Type: \'AWS::EC2::SecurityGroup\'
    Description: Select a Security Group that allows traffic between the load balancer and ECS hosts.

  SubnetId:
    Type: \'List<AWS::EC2::Subnet::Id>\'
    Description: Select at least two subnets in your selected VPC.  

  VpcId:
    Type: \'AWS::EC2::VPC::Id\'
    Description: Select a VPC that allows access from the corporate network.

Resources:
  ECSALB:
    Type: \'AWS::ElasticLoadBalancingV2::LoadBalancer\'
    Properties:
      Name: ECS Risk Rating Application ALB
      Scheme: internet-facing
      LoadBalancerAttributes:
        - Key: idle_timeout.timeout_seconds
          Value: \'30\'
      Subnets: !Ref SubnetId
      SecurityGroups:
        - !Ref ECSLoadBalancerSecurityGroup

  ECSALBListener:
    Type: \'AWS::ElasticLoadBalancingV2::Listener\'
    Properties:
      Certificates: !FindInMap 
        - AWSRegionToCertificate
        - !Ref \'AWS::Region\'
        - CertificateArn
      DefaultActions:
        - Type: forward
          TargetGroupArn: !Ref ECSTargetGroup
      LoadBalancerArn: !Ref ECSALB
      Port: \'443\'
      Protocol: HTTPS

  ECSALBListenerRule:
    Type: \'AWS::ElasticLoadBalancingV2::ListenerRule\'
    DependsOn: ECSALBListener
    Properties:
      Actions:
        - Type: forward
          TargetGroupArn: !Ref ECSTargetGroup
      Conditions:
        - Field: path-pattern
          Values:
            - /
      ListenerArn: !Ref ECSALBListener
      Priority: 1

  ECSTargetGroup:
    Type: \'AWS::ElasticLoadBalancingV2::TargetGroup\'
    DependsOn: ECSALB
    Properties:
      HealthCheckIntervalSeconds: 10
      HealthCheckPath: /
      HealthCheckProtocol: HTTP
      HealthCheckTimeoutSeconds: 5
      HealthyThresholdCount: 2
      Name: ECSTargetGroup
      Port: 80
      Protocol: HTTP
      UnhealthyThresholdCount: 2
      VpcId: !Ref VpcId

Outputs:
  ECSALBDNS:
    Description: Your ALB DNS URL
    Value: !Join 
      - \'\'
      - - !GetAtt 
          - ECSALB
          - DNSName

  ECSTargetGroupArn:
    Description: ECS Target Group Arn
    Value: !Ref ECSTargetGroup

  LoadBalancerFullName:
    Description: ALB Full Name
    Value: !Join
      - \'\'
      - - !GetAtt
          - ECSALB
          - LoadBalancerFullName
"""

risk_rating_cloudwatch_logs_contents = """# This CloudFormation template creates necessary infrastructure in
# AWS CloudWatch to store logs for longer term use troubleshooting
# or for record retention requirements.

AWSTemplateFormatVersion: 2010-09-09

Resources:
  CloudWatchLogsGroup:
    Type: \'AWS::Logs::LogGroup\'
    Properties:
      LogGroupName: !Join 
        - \'-\'
        - - ECSLogGroup
          - !Ref \'AWS::StackName\'
      RetentionInDays: 14

Outputs:
  CloudWatchLogsGroupName:
    Description: The Cloudwatch Logs Group Name
    Value: !Ref CloudWatchLogsGroup
"""

risk_rating_elastic_container_service_cluster_contents = """# This CloudFormation template defines all the necessary infrastructure
# to create an Elastic Container Service (ECS) cluster in AWS and deploy
# the Risk Rating containers to the clusters. 

AWSTemplateFormatVersion: 2010-09-09

Parameters:
  AutoscalingRoleArn:
    Type: String
    Description: Autoscaling Role ARN

  CloudWatchLogsGroupName:
    Type: String
    Description: CloudWatch Logs Group Name

  DesiredCapacity:
    Type: Number
    Default: \'1\'
    Description: Number of EC2 instances to launch in your ECS cluster

  EC2InstanceProfile:
    Type: String
    Description: EC2 Instance Profile

  ECSSecurityGroup:
    Type: String
    Description: Security group to attach to the ECS instances

  ECSTargetGroupArn:
    Type: String
    Description: ECS Target Group Arn

  InstanceType:
    Description: EC2 instance type
    Type: String
    Default: t2.micro
    AllowedValues:
      - t2.micro
      - t2.small
      - t2.medium
      - t2.large
      - m5.large
      - m5.xlarge
      - m5.2xlarge
      - m5.4xlarge
      - m5.10xlarge
      - c5.large
      - c5.xlarge
      - c5.2xlarge
      - c5.4xlarge
      - c5.8xlarge
    ConstraintDescription: Please choose a valid instance type

  KeyName:
    Type: \'AWS::EC2::KeyPair::KeyName\'
    Default: \'risk-rating-application-ssh-key\'
    Description: Name of an existing EC2 Key Pair to enable SSH access to the ECS instances.

  LoadBalancerFullName:
    Type: String
    Description: Full name of the ECS cluster load balancer

  MaxSize:
    Type: Number
    Default: \'4\'
    Description: Maximum number of EC2 instances that can be launched in your ECS cluster

  SubnetId:
    Type: \'List<AWS::EC2::Subnet::Id>\'
    Description: Select at least two subnets in your selected VPC.

  VpcId:
    Type: \'AWS::EC2::VPC::Id\'
    Description: Select a VPC that allows instances to access the Internet.

Mappings:
  AWSRegionToAMI:
    us-east-1:
      AMIID: ami-09bee01cc997a78a6
    us-east-2:
      AMIID: ami-0a9e12068cb98a01d
    us-west-1:
      AMIID: ami-0fa6c8d131a220017
    us-west-2:
      AMIID: ami-078c97cf1cefd1b38

Resources:
  ALB500sAlarmScaleUp:
    Type: \'AWS::CloudWatch::Alarm\'
    Properties:
      EvaluationPeriods: \'1\'
      Statistic: Average
      Threshold: \'10\'
      AlarmDescription: Alarm if our ALB generates too many HTTP 500s.
      Period: \'60\'
      AlarmActions:
        - !Ref ServiceScalingPolicy
      Namespace: AWS/ApplicationELB
      Dimensions:
        - Name: LoadBalancer
          Value: !Ref LoadBalancerFullName
      ComparisonOperator: GreaterThanThreshold
      MetricName: HTTPCode_ELB_5XX_Count

  ContainerInstances:
    Type: \'AWS::AutoScaling::LaunchConfiguration\'
    Properties:
      ImageId: !FindInMap 
        - AWSRegionToAMI
        - !Ref \'AWS::Region\'
        - AMIID
      SecurityGroups:
        - !Ref ECSSecurityGroup
      InstanceType: !Ref InstanceType
      IamInstanceProfile: !Ref EC2InstanceProfile
      KeyName: !Ref KeyName
      UserData: !Base64 
        \'Fn::Join\':
          - \'\'
          - - |
              #!/bin/bash -xe
            - echo ECS_CLUSTER=
            - !Ref ECSCluster
            - |2
               >> /etc/ecs/ecs.config
            - |
              yum install -y aws-cfn-bootstrap
            - \'/opt/aws/bin/cfn-signal -e $? \'
            - \'         --stack \'
            - !Ref \'AWS::StackName\'
            - \'         --resource ECSAutoScalingGroup \'
            - \'         --region \'
            - !Ref \'AWS::Region\'
            - |+

  ECSAutoScalingGroup:
    Type: \'AWS::AutoScaling::AutoScalingGroup\'
    Properties:
      VPCZoneIdentifier: !Ref SubnetId
      LaunchConfigurationName: !Ref ContainerInstances
      MinSize: \'1\'
      MaxSize: !Ref MaxSize
      DesiredCapacity: !Ref DesiredCapacity
    CreationPolicy:
      ResourceSignal:
        Timeout: PT15M
    UpdatePolicy:
      AutoScalingReplacingUpdate:
        WillReplace: \'true\'

  ECSCluster:
    Type: \'AWS::ECS::Cluster\'

  ECSService:
    Type: \'AWS::ECS::Service\'
    Properties:
      Cluster: !Ref ECSCluster
      DesiredCount: \'1\'
      LoadBalancers:
        - ContainerName: risk-rating-application-ui
          ContainerPort: \'80\'
          TargetGroupArn: !Ref ECSTargetGroupArn
      Role: !Ref ECSServiceRole
      TaskDefinition: !Ref RiskRatingApplicationUITaskDefinition

  ECSServiceRole:
    Type: \'AWS::IAM::Role\'
    Properties:
      AssumeRolePolicyDocument:
        Statement:
          - Effect: Allow
            Principal:
              Service:
                - ecs.amazonaws.com
            Action:
              - \'sts:AssumeRole\'
      Path: /
      Policies:
        - PolicyName: ecs-service
          PolicyDocument:
            Statement:
              - Effect: Allow
                Action:
                  - \'elasticloadbalancing:DeregisterInstancesFromLoadBalancer\'
                  - \'elasticloadbalancing:DeregisterTargets\'
                  - \'elasticloadbalancing:Describe*\'
                  - \'elasticloadbalancing:RegisterInstancesWithLoadBalancer\'
                  - \'elasticloadbalancing:RegisterTargets\'
                  - \'ec2:Describe*\'
                  - \'ec2:AuthorizeSecurityGroupIngress\'
                Resource: \'*\'

  RiskRatingApplicationProcessorTaskDefinition:
    Type: \'AWS::ECS::TaskDefinition\'
    Properties:
      Family: !Join 
        - \'\'
        - - !Ref \'AWS::StackName\'
          - \'-risk-rating-application-processor\'
      ContainerDefinitions:
        - Name:  risk-rating-application-processor
          Cpu: \'100\'
          Command:
            - >-
              /usr/local/app run
          Essential: \'true\'
          Image: \'risk-rating-application-processor:2.4\'
          Memory: \'200\'
          LogConfiguration:
            LogDriver: awslogs
            Options:
              awslogs-group: !Ref CloudWatchLogsGroupName
              awslogs-region: !Ref \'AWS::Region\'
              awslogs-stream-prefix: risk-rating-application-processor
          MountPoints:
            - ContainerPath: /usr/local/app
              SourceVolume: risk-rating-application-processor-volume
      ExecutionRoleArn:
        Fn::Sub: "arn:aws:iam::${AWS::AccountId}:role/risk-rating-application-processor"
      Volumes:
        - Name: risk-rating-application-processor-volume

  RiskRatingApplicationUITaskDefinition:
    Type: \'AWS::ECS::TaskDefinition\'
    Properties:
      Family: !Join 
        - \'\'
        - - !Ref \'AWS::StackName\'
          - \'-risk-rating-applicatio-ui\'
      ContainerDefinitions:
        - Name: risk-rating-application-ui
          Cpu: \'10\'
          Essential: \'true\'
          Image: \'risk-rating-application-ui:2.4\'
          Memory: \'300\'
          LogConfiguration:
            LogDriver: awslogs
            Options:
              awslogs-group: !Ref CloudWatchLogsGroupName
              awslogs-region: !Ref \'AWS::Region\'
              awslogs-stream-prefix: risk-rating-application-ui
          PortMappings:
            - ContainerPort: 80
      ExecutionRoleArn:
        Fn::Sub: "arn:aws:iam::${AWS::AccountId}:role/risk-rating-application-ui"
      Volumes:
        - Name: risk-rating-application-processor-volume

  ServiceScalingTarget:
    Type: \'AWS::ApplicationAutoScaling::ScalableTarget\'
    Properties:
      MaxCapacity: !Ref MaxSize
      MinCapacity: 1
      ResourceId: !Join
        - \'\'
        - - service/
          - !Ref ECSCluster
          - /
          - !GetAtt
            - ECSService
            - Name
      RoleARN: !Ref AutoscalingRoleArn
      ScalableDimension: \'ecs:service:DesiredCount\'
      ServiceNamespace: ecs

  ServiceScalingPolicy:
    Type: \'AWS::ApplicationAutoScaling::ScalingPolicy\'
    Properties:
      PolicyName: AStepPolicy
      PolicyType: StepScaling
      ScalingTargetId: !Ref ServiceScalingTarget
      StepScalingPolicyConfiguration:
        AdjustmentType: PercentChangeInCapacity
        Cooldown: 60
        MetricAggregationType: Average
        StepAdjustments:
          - MetricIntervalLowerBound: 0
            ScalingAdjustment: 200

Outputs:
  ECSCluster:
    Value: !Ref ECSCluster
  
  ECSService:
    Value: !Ref ECSService

  RiskRatingApplicationProcessorTaskDefinition:
    Value: !Ref RiskRatingApplicationProcessorTaskDefinition

  RiskRatingApplicationUITaskDefinition:
    Value: !Ref RiskRatingApplicationUITaskDefinition
"""

risk_rating_security_groups_contents = """# This CloudFormation template defines all security group rules for the Risk
# Rating application which define network ingress and egress to the cluster
# infrastructure and application containers.

AWSTemplateFormatVersion: 2010-09-09

Parameters:
  SubnetId:
    Type: \'List<AWS::EC2::Subnet::Id>\'
    Description: Select at least two subnets in your selected VPC.

  VpcId:
    Type: \'AWS::EC2::VPC::Id\'
    Description: Select a VPC that allows instances to access the Internet.

Resources:
  # Create load balancer security group
  ECSLoadBalancerSecurityGroup:
    Type: \'AWS::EC2::SecurityGroup\'
    Properties:
      GroupDescription: ECS Risk Rating Application Load Balancer Security Group
      VpcId: !Ref VpcId

  # Create ECS host security group
  ECSSecurityGroup:
    Type: \'AWS::EC2::SecurityGroup\'
    Properties:
      GroupDescription: ECS Host Security Group
      VpcId: !Ref VpcId

  # Allow dynamic ports between container instances
  ECSSecurityGroupALBPorts:
    Type: \'AWS::EC2::SecurityGroupIngress\'
    Properties:
      GroupId: !Ref ECSSecurityGroup
      IpProtocol: tcp
      FromPort: 31000
      ToPort: 61000
      SourceSecurityGroupId: !Ref ECSSecurityGroup

  # Allow traffic from load balancer to ECS host
  ECSSecurityGroupHTTPInbound:
    Type: \'AWS::EC2::SecurityGroupIngress\'
    Properties:
      GroupId: !Ref ECSSecurityGroup
      IpProtocol: tcp
      FromPort: 80
      ToPort: 80
      SourceSecurityGroupId: !Ref ECSLoadBalancerSecurityGroup

  # Allow SSH for troubleshooting issues with clusters
  ECSSecurityGroupSSHInbound:
    Type: \'AWS::EC2::SecurityGroupIngress\'
    Properties:
      GroupId: !Ref ECSSecurityGroup
      IpProtocol: tcp
      FromPort: 22
      ToPort: 22
      CidrIp: 0.0.0.0/0

Outputs:
  ECSSecurityGroup:
    Description: ECS Host Security Group
    Value: !Ref ECSSecurityGroup

  ECSLoadBalancerSecurityGroup:
    Description: ECS Risk Rating Application Load Balancer Security Group
    Value: !Ref ECSLoadBalancerSecurityGroup
"""

file_contents = {
  'prompt.md': prompt_contents,
  'Makefile': makefile_contents,
  'iam/risk-rating-s3-access-nonproduction.json': risk_rating_s3_access_nonproduction_contents,
  'iam/risk-rating-s3-access-production.json': risk_rating_s3_access_production_contents,
  'cfn/orchestrator.yml': orchestrator_contents,
  'cfn/risk-rating-application-iam.yml': risk_rating_application_iam_contents,
  'cfn/risk-rating-application-load-balancer.yml': risk_rating_application_load_balancer_contents,
  'cfn/risk-rating-cloudwatch-logs.yml': risk_rating_cloudwatch_logs_contents,
  'cfn/risk-rating-elastic-container-service-cluster.yml': risk_rating_elastic_container_service_cluster_contents,
  'cfn/risk-rating-security-groups.yml': risk_rating_security_groups_contents
}

# Create directories
os.makedirs('cfn', exist_ok=True)
os.makedirs('iam', exist_ok=True)

for file_name, content in file_contents.items():
  with open(file_name, 'w') as file:
    file.write(content)

print("Directories and prompt created successfully.")