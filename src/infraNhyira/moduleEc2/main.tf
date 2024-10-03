# resource "aws_key_pair" "generated_key" {
#   key_name   = var.key_pair_name
#   public_key = file("${path.module}/tf_key.pem.pub")
# }

resource "aws_instance" "private_ec2_backend_1" {
  ami               = var.ami
  availability_zone = var.az
  instance_type     = var.inst_type
  ebs_block_device {
    device_name = "/dev/sda1"
    volume_size = 16
    volume_type = "gp3"
  }
  key_name                    = "shh_key"
  subnet_id                   = var.subnet_id
  associate_public_ip_address = false
  vpc_security_group_ids      = [var.sg_id]
  tags = {
    Name = "private-ec2-01"
  }

user_data = base64encode(<<-EOF
#!/bin/bash
exec > /var/log/user_data.log 2>&1
set -x

# Atualiza pacotes e instala Java
sudo apt-get update -y
sudo apt-get install -y default-jdk
sudo apt-get install -y git

# Atualiza o repositório novamente
sudo apt-get update -y

# Instala o Docker
sudo apt-get install -y docker-ce

# Verifica se o Docker está instalado corretamente
sudo systemctl start docker
sudo systemctl enable docker

# Instala Docker Compose
sudo curl -L "https://github.com/docker/compose/releases/download/1.29.2/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose
sudo chmod +x /usr/local/bin/docker-compose

# Cria o diretório se não existir
mkdir -p /home/ubuntu/AWS

# Clonar ou atualizar o repositório
cd /home/ubuntu/AWS || {
  git clone https://github.com/nhyira-group5/Back-End.git /home/ubuntu/AWS
}

cd /home/ubuntu/AWS
git pull origin main  # Atualiza o repositório

# Navega até o diretório do projeto
cd /home/ubuntu/AWS

# Constrói a imagem Docker usando o Dockerfile
sudo docker build -t nhyira-api .

# Executa o Docker Compose para iniciar os serviços
 sudo docker-compose -f /home/ubuntu/AWS/docker-compose.yml up -d

   EOF
  )
}



resource "aws_instance" "private_ec2_backend_2" {
  ami               = var.ami
  availability_zone = var.az
  instance_type     = var.inst_type
  ebs_block_device {
    device_name = "/dev/sda1"
    volume_size = 16
    volume_type = "gp3"
  }
  key_name                    = "shh_key"
  subnet_id                   = var.subnet_id
  associate_public_ip_address = false
  vpc_security_group_ids      = [var.sg_id]
  tags = {
    Name = "private-ec2-02"
  }
user_data = base64encode(<<-EOF
#!/bin/bash
exec > /var/log/user_data.log 2>&1
set -x

# Atualiza pacotes e instala Java
sudo apt-get update -y
sudo apt-get install -y default-jdk
sudo apt-get install -y git

# Atualiza o repositório novamente
sudo apt-get update -y

# Instala o Docker
sudo apt-get install -y docker-ce

# Verifica se o Docker está instalado corretamente
sudo systemctl start docker
sudo systemctl enable docker

# Instala Docker Compose
sudo curl -L "https://github.com/docker/compose/releases/download/1.29.2/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose
sudo chmod +x /usr/local/bin/docker-compose

# Cria o diretório se não existir
mkdir -p /home/ubuntu/AWS

# Clonar ou atualizar o repositório
cd /home/ubuntu/AWS || {
  git clone https://github.com/nhyira-group5/Back-End.git /home/ubuntu/AWS
}

cd /home/ubuntu/AWS
git pull origin main  # Atualiza o repositório

# Navega até o diretório do projeto
cd /home/ubuntu/AWS

# Constrói a imagem Docker usando o Dockerfile
sudo docker build -t nhyira-api .

# Executa o Docker Compose para iniciar os serviços
 sudo docker-compose -f /home/ubuntu/AWS/docker-compose.yml up -d
   EOF
  )
}

