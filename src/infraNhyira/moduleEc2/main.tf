# Define o par de chaves (opcional)
# resource "aws_key_pair" "generated_key" {
#   key_name   = var.key_pair_name
#   public_key = file("${path.module}/tf_key.pem.pub")
# }

resource "aws_instance" "public_ec2_backend_1" {
  ami               = var.ami
  availability_zone = var.az
  instance_type     = var.inst_type

  ebs_block_device {
    device_name = "/dev/sda1"
    volume_size = 16
    volume_type = "gp3"
  }

  key_name                    = "ti_key"
  subnet_id                   = var.subnet_id
  associate_public_ip_address = true
  vpc_security_group_ids      = [var.sg_id]

  tags = {
    Name = "private-ec2-01"
  }

 user_data = base64encode(<<-EOF
    #!/bin/bash

    # Cria a pasta aws
    sudo mkdir -p /home/ubuntu/aws

    # Clona ou atualiza o repositório
    if [ ! -d "/home/ubuntu/aws/.git" ]; then
      sudo git clone https://PERSONAL_ACCESS_TOKEN@github.com/nhyira-group5/Back-End.git /home/ubuntu/aws
    else
      cd /home/ubuntu/aws
      sudo git pull origin main  # Atualiza o repositório
    fi

    # Atualiza o sistema e instala MySQL
    sudo apt update
    sudo apt install -y mysql-server

    # Instala Docker
    sudo apt install -y docker.io 

    # Habilita e inicia o serviço Docker
    sudo systemctl start docker
    sudo systemctl enable docker

    # Instala o Docker Compose
    sudo apt install -y docker-compose
    
    # Baixar a versão mais recente do Docker Compose
    DOCKER_COMPOSE_VERSION=$(curl -s https://api.github.com/repos/docker/compose/releases/latest | grep -oP '"tag_name": "\K[^\"]+')
    sudo curl -L "https://github.com/docker/compose/releases/download/$DOCKER_COMPOSE_VERSION/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose

    # Dar permissão de execução ao binário do Docker Compose
    sudo chmod +x /usr/local/bin/docker-compose

    # Verifique se a instalação foi bem-sucedida
    docker-compose --version

    # Atualiza pacotes e instala Java
    sudo apt-get install -y default-jdk

    # Navega até o diretório do projeto
    cd /home/ubuntu/aws

    # Constrói a imagem Docker usando o Dockerfile
    sudo docker build -t nhyira-api .

    # Executa o Docker Compose para iniciar os serviços
    sudo docker-compose up --build -d
EOF
  )
}

# Instância privada
resource "aws_instance" "private_ec2_backend_2" {
  ami               = var.ami
  availability_zone = var.az
  instance_type     = var.inst_type

  ebs_block_device {
    device_name = "/dev/sda1"
    volume_size = 16
    volume_type = "gp3"
  }

  key_name                    = "ti_key"
  subnet_id                   = var.subnet_id # Subnet privada
  associate_public_ip_address = true
  vpc_security_group_ids      = [var.sg_id]

  tags = {
    Name = "private-ec2-02"
  }

 user_data = base64encode(<<-EOF
    #!/bin/bash

    # Cria a pasta aws
    sudo mkdir -p /home/ubuntu/aws

    # Clona ou atualiza o repositório
    if [ ! -d "/home/ubuntu/aws/.git" ]; then
      sudo git clone https://PERSONAL_ACCESS_TOKEN@github.com/nhyira-group5/Back-End.git /home/ubuntu/aws
    else
      cd /home/ubuntu/aws
      sudo git pull origin main  # Atualiza o repositório
    fi

    # Atualiza o sistema e instala MySQL
    sudo apt update
    sudo apt install -y mysql-server

    # Instala Docker
    sudo apt install -y docker.io 

    # Habilita e inicia o serviço Docker
    sudo systemctl start docker
    sudo systemctl enable docker

    # Instala o Docker Compose
    sudo apt install -y docker-compose
    
    # Baixar a versão mais recente do Docker Compose
    DOCKER_COMPOSE_VERSION=$(curl -s https://api.github.com/repos/docker/compose/releases/latest | grep -oP '"tag_name": "\K[^\"]+')
    sudo curl -L "https://github.com/docker/compose/releases/download/$DOCKER_COMPOSE_VERSION/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose

    # Dar permissão de execução ao binário do Docker Compose
    sudo chmod +x /usr/local/bin/docker-compose

    # Verifique se a instalação foi bem-sucedida
    docker-compose --version

    # Atualiza pacotes e instala Java
    sudo apt-get install -y default-jdk

    # Navega até o diretório do projeto
    cd /home/ubuntu/aws

    # Constrói a imagem Docker usando o Dockerfile
    sudo docker build -t nhyira-api .

    # Executa o Docker Compose para iniciar os serviços
    sudo docker-compose up --build -d
EOF
  )
}

resource "aws_eip_association" "eip_assoc_01" {
  instance_id  = aws_instance.public_ec2_backend_1.id
  allocation_id = "eipalloc-0acf2f0ec8a2d7a08" # ID de alocação
}

resource "aws_eip_association" "eip_assoc_02" {
  instance_id   = aws_instance.private_ec2_backend_2.id
  allocation_id = "eipalloc-0b9a802e2513935de"  # id de alocação
}
