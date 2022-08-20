FROM nginx:alpine

## 创建目录，并使用它作为工作目录
RUN mkdir -p /usr/share/nginx/html/yudao-ui-admin
WORKDIR /usr/share/nginx/html/yudao-ui-admin

COPY ./dist ./
COPY ./nginx/yudao-ui-admin.conf /etc/nginx/conf.d/default.conf

## 设置 TZ 时区
ENV TZ=Asia/Shanghai

## 暴露前端项目的 80 端口
EXPOSE 80
