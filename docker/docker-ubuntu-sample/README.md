ubuntu:16.04 설치

참조 : http://thebluecheese.tistory.com/22
참조 : https://www.rabbitmq.com/clustering.html


master : testubuntu1

slave : testubuntu2, testubuntu3

ssh : root/root


##########################################################################################


1. [master, slave] default setup

apt-get update -y

apt-get upgrade -y

apt-get install vim -y

apt-get install net-tools

apt-get update && apt-get install -y sudo

adduser ksb
password

usermod -aG sudo ksb

alias grep='grep --color=auto'


2. [master, slave] ksb login 


3. [master, slave] install rabbitmq 

sudo apt-get install -y erlang

sudo apt-get install rabbitmq-server -y

sudo systemctl enable rabbitmq-server

ps -ef | grep rabbitmq



4. [master, slave] start rabbitmq 

sudo service rabbitmq-server start

ps -ef | grep rabbitmq

sudo rabbitmq-plugins enable rabbitmq_management

sudo rabbitmqctl add_user test password

sudo rabbitmqctl set_user_tags test administrator

sudo rabbitmqctl set_permissions -p / test ".*" ".*" ".*"

netstat -tnlp | grep 5672

sudo rabbitmqctl cluster_status


4. [master] set cookie (모든 클러스터 노드에는 동일한 쿠키가 있어야함)

cd /var/lib/rabbitmq

sudo scp .erlang.cookie ksb@testubuntu2:/var/lib/rabbitmq

sudo scp .erlang.cookie ksb@testubuntu3:/var/lib/rabbitmq

[slave]에 권한이 없는 경우 cd /var/lib/rabbitmq
sudo chmod 777 .erlang.cookie
sudo chmod 400 .erlang.cookie


5. [slave] set host (반드시 [master]의 hostname 을 사용)

sudo vi /etc/hosts

172.21.0.4      testubuntu1


6  [slave]

sudo rabbitmqctl cluster_status

sudo service rabbitmq-server stop

sudo service rabbitmq-server start

ps -ef | grep rabbitmq
(안되면 kill -9 하고 시작)

sudo rabbitmqctl cluster_status


7. [master, slave] restart rabbitmq 

sudo rabbitmqctl cluster_status


8  [slave-1]

슬레이브는 하나씩 클러스터링하고 연결을 확인한다.

첫번째는 testubuntu1에 붙는다.

sudo rabbitmqctl stop_app

sudo rabbitmqctl join_cluster --ram rabbit@testubuntu1

sudo rabbitmqctl cluster_status

sudo rabbitmqctl start_app

sudo rabbitmqctl cluster_status


9  [slave-2]

슬레이브는 하나씩 클러스터링하고 연결을 확인한다.

두번째는 testubuntu2에 붙는다.

sudo rabbitmqctl stop_app

sudo rabbitmqctl join_cluster --ram rabbit@testubuntu2

sudo rabbitmqctl cluster_status

sudo rabbitmqctl start_app

sudo rabbitmqctl cluster_status


##########################################################################################
##########################################################################################
##########################################################################################


10.0.2.15
ssh ksb@10.0.2.4
ssh ksb@10.0.2.5


[master,slave]
sudo service rabbitmq-server stop
sudo service rabbitmq-server start

sudo rabbitmqctl cluster_status
sudo rabbitmqctl status

[slave]
cd /var/lib/rabbitmq
sudo chmod 777 .erlang.cookie

[master]
cd /var/lib/rabbitmq
sudo scp .erlang.cookie ksb@10.0.2.4:/var/lib/rabbitmq
sudo scp .erlang.cookie ksb@10.0.2.5:/var/lib/rabbitmq

[slave]
sudo chmod 400 .erlang.cookie

[slave]
sudo vi /etc/hosts
10.0.2.15       testubuntu1

[slave]
sudo service rabbitmq-server stop
sudo service rabbitmq-server start

sudo rabbitmqctl cluster_status

sudo rabbitmqctl stop_app

sudo rabbitmqctl join_cluster --ram rabbit@testubuntu1

sudo rabbitmqctl cluster_status

sudo rabbitmqctl start_app



[master]
sudo rabbitmqctl cluster_status


#######################################################


sudo service rabbitmq-server start
sudo service rabbitmq-server stop
sudo rabbitmq-plugins enable rabbitmq_management

netstat -tnlp | grep 5672

sudo rabbitmqctl add_user test password
sudo rabbitmqctl set_user_tags test administrator
sudo rabbitmqctl set_permissions -p / test ".*" ".*" ".*"

#######################################################









