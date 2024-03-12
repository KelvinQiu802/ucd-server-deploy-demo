# Deployment Demo

## Docker

```shell
$ docker compose up -d
$ docker compose down
```

## Application Port

- **Server:** 8080:8080
- **Client:** 3000:3000
- **MySQL:** 3307:3306

## Docker Compose

```dockerfile
version: '3'
services:
  server:
    image: kelvinqiu802/ucd-server-deploy-demo-server
    depends_on:
      - mysql_container
    ports:
      - 8080:8080
  client:
    image: kelvinqiu802/ucd-server-deploy-demo-client
    ports:
      - 3000:3000
  mysql_container:
    image: mysql:latest
    restart: always
    ports:
      - 3307:3306
    environment:
      MYSQL_ROOT_PASSWORD: password
      MYSQL_DATABASE: demo
      MYSQL_USER: kelvin
      MYSQL_PASSWORD: password
    volumes:
      - db-data:/var/lib/mysql
volumes:
  db-data:
```

## Ngnix Config

```nginx
upstream frontend_server {
    server 127.0.0.1:3000;
}

upstream backend_server {
    server 127.0.0.1:8080;
}

server {
    listen 80;
    server_name csi420-02-vm9.ucd.ie;

    location / {
        proxy_pass http://frontend_server;
        proxy_set_header Host $host;
        proxy_set_header X-Real-IP $remote_addr;
        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
    }

    location /api {
        proxy_pass http://backend_server;
        proxy_set_header Host $host;
        proxy_set_header X-Real-IP $remote_addr;
        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
    }
}
```

