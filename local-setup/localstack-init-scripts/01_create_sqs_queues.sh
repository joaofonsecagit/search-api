#!/usr/bin/env bash

echo "##### Creating SQS queues #####"

awslocal sqs create-queue --queue-name some-queue-name

echo "##### Listing SQS queues #####"

awslocal sqs list-queues