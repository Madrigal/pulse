# Pulse

Follow your favorite topics from your favorite sources such as HN and Reddit.
You can call it either via the command line or set it up in an AWS Lambda
function to periodically poll the sites.

## How to build
Run `gradle uberJar`. Lambda will be located at `build/libs/XXX-all.jar`. You can upload this into Lambda directly.

## Prerequisites
- Setup Lambda
- Setup SES

## Current implementations
- HN
- Reddit
