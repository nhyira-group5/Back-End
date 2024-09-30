

# Gera uma chave SSH (opcional, se você já tiver uma)
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
    Name = "private-ecec-01"
  }

  user_data = base64encode(<<-EOF
    #!/bin/bash
    exec > /var/log/user_data.log 2>&1
    set -x

    # Cria o diretório AWS
    mkdir -p /home/ubuntu/AWS

    # Exporta a variável de ambiente
    export DOCKERHUB_USERNAME=${var.dockerhub_username}

    # Atualiza pacotes e instala Java
    sudo apt-get update
    sudo apt-get install -y default-jdk || { echo "Falha ao instalar o Java"; exit 1; }

    # Instala Docker
    sudo apt-get install -y docker.io || { echo "Falha ao instalar o Docker"; exit 1; }

    # Instala Docker Compose
    sudo curl -L "https://github.com/docker/compose/releases/download/1.29.2/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose
    sudo chmod +x /usr/local/bin/docker-compose

    # Inicia e habilita Docker
    sudo systemctl start docker
    sudo systemctl enable docker

    # Executa comandos Docker
    sudo docker pull $DOCKERHUB_USERNAME/nhyira-api || { echo "Falha ao puxar a imagem do Docker"; exit 1; }

    # Copia o docker-compose.yml para o diretório correto
    sudo cp /path/to/your/docker-compose.yml /home/ubuntu/AWS/docker-compose.yml

    # Subir os serviços com Docker Compose
    cd /home/ubuntu/AWS
    sudo docker-compose up -d || { echo "Falha ao subir os serviços com Docker Compose"; exit 1; }
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

    # Cria o diretório AWS
    mkdir -p /home/ubuntu/AWS

    # Exporta a variável de ambiente
    export DOCKERHUB_USERNAME=${var.dockerhub_username}

    # Atualiza pacotes e instala Java
    sudo apt-get update
    sudo apt-get install -y default-jdk || { echo "Falha ao instalar o Java"; exit 1; }

    # Instala Docker
    sudo apt-get install -y docker.io || { echo "Falha ao instalar o Docker"; exit 1; }

    # Instala Docker Compose
    sudo curl -L "https://github.com/docker/compose/releases/download/1.29.2/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose
    sudo chmod +x /usr/local/bin/docker-compose

    # Inicia e habilita Docker
    sudo systemctl start docker
    sudo systemctl enable docker

    # Executa comandos Docker
    sudo docker pull $DOCKERHUB_USERNAME/nhyira-api || { echo "Falha ao puxar a imagem do Docker"; exit 1; }

    # Copia o docker-compose.yml para o diretório correto
    sudo cp /path/to/your/docker-compose.yml /home/ubuntu/AWS/docker-compose.yml

    # Subir os serviços com Docker Compose
    cd /home/ubuntu/AWS
    sudo docker-compose up -d || { echo "Falha ao subir os serviços com Docker Compose"; exit 1; }
    EOF
  )
}

resource "aws_eip_association" "eip_assoc_01" {
  instance_id   = aws_instance.public_ec2_backend-1.id
  allocation_id  = "eipalloc-04c103f2c5910a4cb" # ID de alocação do EIP
}
