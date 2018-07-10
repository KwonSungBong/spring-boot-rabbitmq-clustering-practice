"# spring-boot-rabbitmq-clustering-practice" 


https://github.com/pardahlman/docker-rabbitmq-cluster


15671,15672,15673

5671,5672,5673

guest/guest


211.49.172.81:5671,211.49.172.81:5672,211.49.172.81:5673


http://abh0518.net/tok/?p=411


############################################################


https://rorlab.gitbooks.io/railsguidebook/content/appendices/ubuntu16server.html


ksb/password


master 192.168.35.141

slave 192.168.35.171

slave 192.168.35.106


ssh ksb@192.168.35.141

ssh ksb@192.168.35.171

ssh ksb@192.168.35.106


erlang, rabbit-mq 를 순서대로 설치한다.
sudo apt-get update
sudo apt-get upgrade
sudo apt-get install -y erlang
sudo apt-get install rabbitmq-server
sudo systemctl enable rabbitmq-server

서비스 실행 및 중지
sudo service rabbitmq-server start
sudo service rabbitmq-server stop


slave

192.168.35.141  mqmaster

sudo rabbitmqctl stop_app


master

cd /var/lib/rabbitmq

sudo scp .erlang.cookie ksb@192.168.35.171:/var/lib/rabbitmq

sudo scp .erlang.cookie ksb@192.168.35.106:/var/lib/rabbitmq

sudo chmod 777 .erlang.cookie


slave

sudo rabbitmqctl join_cluster --ram rabbit@ubuntu




sudo scp .erlang.cookie ksb@192.168.35.171:~


sudo scp .erlang.cookie ksb@192.168.35.106:~


hostnamectl set-hostname ubuntu1
hostnamectl set-hostname ubuntu2


rabbitmqctl status




