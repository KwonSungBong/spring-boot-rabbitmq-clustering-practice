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



#####################################################################


master,slave,slave

ssh ksb@192.168.35.175

ssh ksb@192.168.35.171

ssh ksb@192.168.35.106



sudo apt-get update
sudo apt-get upgrade
sudo apt-get install -y erlang
sudo apt-get install rabbitmq-server
sudo systemctl enable rabbitmq-server



#####################################################################


master,slave,slave

ssh ksb@192.168.35.175

ssh ksb@192.168.35.65

ssh ksb@192.168.35.3



sudo apt-get update -y
sudo apt-get upgrade -y
sudo apt-get install -y erlang
sudo apt-get install rabbitmq-server
sudo systemctl enable rabbitmq-server

ps -ef | grep rabbitmq

sudo rabbitmqctl cluster_status


cd /var/lib/rabbitmq
sudo chmod 777 .erlang.cookie
sudo scp .erlang.cookie ksb@192.168.35.65:/var/lib/rabbitmq
sudo scp .erlang.cookie ksb@192.168.35.3:/var/lib/rabbitmq
sudo chmod 400 .erlang.cookie

sudo vi /etc/hosts
192.168.35.175  testubuntu1

sudo rabbitmqctl cluster_status

sudo service rabbitmq-server stop
sudo service rabbitmq-server start

sudo rabbitmqctl cluster_status

sudo rabbitmqctl stop_app

sudo rabbitmqctl join_cluster --ram rabbit@testubuntu1
sudo rabbitmqctl join_cluster --ram rabbit@testubuntu3

sudo rabbitmqctl start_app


### reset ###

sudo rabbitmqctl stop_app

sudo rabbitmqctl reset

sudo rabbitmqctl start_app


#### crash ####

sudo rabbitmqctl reset
sudo service rabbitmq-server stop
sudo service rabbitmq-server start
sudo rabbitmqctl stop_app
sudo rabbitmqctl cluster_statuss
sudo rabbitmqctl join_cluster --ram rabbit@testubuntu1


###########################

마스터에 동시에 두개의 슬래이브가 Clustering 되지 않을 때   : 1 ← 2 ← 3 형태의 커넥션으로 Clustering 을 설정한다.


