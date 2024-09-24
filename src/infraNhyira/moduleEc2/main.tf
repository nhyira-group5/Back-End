# resource "aws_key_pair" "generated_key" {
#   key_name   = var.key_pair_name
#   public_key = file("${path.module}/tf_key.pem.pub")
# }

resource "aws_instance" "public_ec2_backend-1" {
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

    export DOCKERHUB_USERNAME=${var.dockerhub_username}

    # Atualizar pacotes e instalar Java
    sudo apt-get update
    sudo apt-get install -y default-jdk

    # Instalar Docker
    sudo apt-get install -y docker.io
    #instalar docker-compose
    sudo curl -L "https://github.com/docker/compose/releases/download/1.29.2/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose
    sudo chmod +x /usr/local/bin/docker-compose
    docker-compose --version

    # Iniciar e habilitar Docker
    sudo systemctl start docker
    sudo systemctl enable docker

    # Executar comandos Docker
    sudo docker pull $DOCKERHUB_USERNAME/nhyira-api
    sudo docker-compose -f /home/ubuntu/AWS/docker-compose.yml up -d
    EOF
  )
}

resource "aws_instance" "public_ec2_backend-2" {
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

    export DOCKERHUB_USERNAME=${var.dockerhub_username}

    # Atualizar pacotes e instalar Java
    sudo apt-get update
    sudo apt-get install -y default-jdk

    # Instalar Docker
    sudo apt-get install -y docker.io

    #instalar docker-compose
    sudo curl -L "https://github.com/docker/compose/releases/download/1.29.2/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose
    sudo chmod +x /usr/local/bin/docker-compose
    docker-compose --version

    # Iniciar e habilitar Docker
    sudo systemctl start docker
    sudo systemctl enable docker

    # Executar comandos Docker
    sudo docker pull $DOCKERHUB_USERNAME/nhyira-api
    sudo docker-compose -f /home/ubuntu/AWS/docker-compose.yml up -d
    EOF
  )
}

resource "aws_eip_association" "eip_assoc_01" {
  instance_id   = aws_instance.public_ec2_backend-1.id
  allocation_id = "eipalloc-0f93c5511057aa5a5"
}