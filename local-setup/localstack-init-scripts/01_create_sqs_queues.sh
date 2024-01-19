#!/usr/bin/env bash

echo "##### Creating SQS queues #####"

awslocal sqs create-queue --queue-name some-queuein
awslocal sqs create-queue --queue-name some-queueout

echo "##### Listing SQS queues #####"

awslocal sqs list-queues