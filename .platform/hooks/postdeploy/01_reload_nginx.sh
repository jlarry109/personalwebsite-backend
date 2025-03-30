#!/bin/bash
echo "Reloading Nginx..."

set -e

# Test and restart NGINX
nginx -t && systemctl restart nginx
