# Directory containing CloudFormation templates
TEMPLATE_DIR := ./cfn

# Llist of CloudFormation templates to validate
TEMPLATE_FILES := $(wildcard $(TEMPLATE_DIR)/*.yml)

# Since the template validation does not produce files, we use .PHONY
.PHONY: validate

validate: $(TEMPLATE_FILES)
	@echo "Validating CloudFormation templates..."
	@for template in $^; do 		aws cloudformation validate-template --output table --no-cli-pager --template-body "file://$$template"; 		if [ $$? -eq 0 ]; then 			echo "Validation successful for $$template"; 		else 			echo "Validation failed for $$template"; 			exit 1; 		fi; 	done

check_delete:
	@echo -n "Are you sure? [y/N] " && read ans && [ $${ans:-N} = y ]

# Corrected deploy-stack-nonproduction and deploy-stack-production targets
deploy-stack-nonproduction: validate
    aws cloudformation create-stack         --stack-name risk-rating-application-nonproduction-stack         --template-body s3://risk-rating-infrastructure-s3-bucket-nonproduction/cfn/orchestrator.yml         --parameters             ParameterKey=DesiredCapacity,ParameterValue=1             ParameterKey=InstanceType,ParameterValue=t2.micro             ParameterKey=MaxSize,ParameterValue=4             ParameterKey=KeyName,ParameterValue=risk-rating-nonprod-key             ParameterKey=SubnetId,ParameterValue=subnet-1a2b3c4d5e6f78901             ParameterKey=VpcId,ParameterValue=vpc-1a2b3c4d5e6f78901

deploy-stack-production: validate
    aws cloudformation create-stack         --stack-name risk-rating-application-production-stack         --template-body s3://risk-rating-infrastructure-s3-bucket-production/cfn/orchestrator.yml         --parameters             ParameterKey=DesiredCapacity,ParameterValue=4             ParameterKey=InstanceType,ParameterValue=c5.4xlarge             ParameterKey=MaxSize,ParameterValue=4             ParameterKey=KeyName,ParameterValue=risk-rating-prod-key             ParameterKey=SubnetId,ParameterValue=subnet-0a1b2c3d4e5f67890             ParameterKey=VpcId,ParameterValue=vpc-0a1b2c3d4e5f67890


upload-cloudformation-nonproduction: validate
	aws s3 cp ./cfn/* s3://risk-rating-infrastructure-s3-bucket-nonproduction/cfn/ --recursive

upload-cloudformation-production: validate
	aws s3 cp ./cfn/* s3://risk-rating-infrastructure-s3-bucket-production/cfn/ --recursive
