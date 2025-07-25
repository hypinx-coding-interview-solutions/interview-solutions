# Risk Rating Application

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
