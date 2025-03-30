#!/bin/bash
echo "Reloading Nginx..."

set -e

# Append custom NGINX config inclusion
echo "include /var/app/current/.platform/nginx/conf.d/*.conf;" >> /etc/nginx/nginx.conf

# Test and restart NGINX
nginx -t && systemctl restart nginx
