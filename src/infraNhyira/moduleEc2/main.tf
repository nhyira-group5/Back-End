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

    # Atualiza pacotes e instala dependências
    sudo apt-get update -y
    sudo apt-get install -y apt-transport-https ca-certificates curl software-properties-common

    # Adiciona o repositório do Docker
    if ! curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -; then
      echo "Falha ao adicionar a chave do Docker" >&2
      exit 1
    fi

    echo "deb [arch=amd64] https://download.docker.com/linux/ubuntu \$(lsb_release -cs) stable" | sudo tee /etc/apt/sources.list.d/docker.list

    # Atualiza novamente e instala o Docker
    sudo apt-get update -y
    if ! sudo apt-get install -y docker-ce; then
      echo "Falha ao instalar o Docker" >&2
      exit 1
    fi

    # Verifica se o Docker está instalado corretamente
    sudo systemctl start docker
    sudo systemctl enable docker

    # Instala Docker Compose
    if ! sudo curl -L "https://github.com/docker/compose/releases/download/1.29.2/docker-compose-\$(uname -s)-\$(uname -m)" -o /usr/local/bin/docker-compose; then
      echo "Falha ao baixar Docker Compose" >&2
      exit 1
    fi
    sudo chmod +x /usr/local/bin/docker-compose

    # Cria o diretório se não existir
    mkdir -p /home/ubuntu/AWS

    # Clonar ou atualizar o repositório
    if [ ! -d "/home/ubuntu/AWS/.git" ]; then
      if ! git clone https://github.com/nhyira-group5/Back-End.git /home/ubuntu/AWS; then
        echo "Falha ao clonar o repositório" >&2
        exit 1
      fi
    else
      cd /home/ubuntu/AWS
      if ! git pull origin main; then
        echo "Falha ao atualizar o repositório" >&2
        exit 1
      fi
    fi

    # Navega até o diretório do projeto e constrói a imagem Docker
    cd /home/ubuntu/AWS
    if ! sudo docker build -t nhyira-api .; then
      echo "Falha ao construir a imagem Docker" >&2
      exit 1
    fi

    # Executa o Docker Compose para iniciar os serviços
    if ! sudo docker-compose -f /home/ubuntu/AWS/docker-compose.yml up -d; then
      echo "Falha ao executar o Docker Compose" >&2
      exit 1
    fi
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

    # Atualiza pacotes e instala dependências
    sudo apt-get update -y
    sudo apt-get install -y apt-transport-https ca-certificates curl software-properties-common

    # Adiciona o repositório do Docker
    if ! curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -; then
      echo "Falha ao adicionar a chave do Docker" >&2
      exit 1
    fi

    echo "deb [arch=amd64] https://download.docker.com/linux/ubuntu \$(lsb_release -cs) stable" | sudo tee /etc/apt/sources.list.d/docker.list

    # Atualiza novamente e instala o Docker
    sudo apt-get update -y
    if ! sudo apt-get install -y docker-ce; then
      echo "Falha ao instalar o Docker" >&2
      exit 1
    fi

    # Verifica se o Docker está instalado corretamente
    sudo systemctl start docker
    sudo systemctl enable docker

    # Instala Docker Compose
    if ! sudo curl -L "https://github.com/docker/compose/releases/download/1.29.2/docker-compose-\$(uname -s)-\$(uname -m)" -o /usr/local/bin/docker-compose; then
      echo "Falha ao baixar Docker Compose" >&2
      exit 1
    fi
    sudo chmod +x /usr/local/bin/docker-compose

    # Cria o diretório se não existir
    mkdir -p /home/ubuntu/AWS

    # Clonar ou atualizar o repositório
    if [ ! -d "/home/ubuntu/AWS/.git" ]; then
      if ! git clone https://github.com/nhyira-group5/Back-End.git /home/ubuntu/AWS; then
        echo "Falha ao clonar o repositório" >&2
        exit 1
      fi
    else
      cd /home/ubuntu/AWS
      if ! git pull origin main; then
        echo "Falha ao atualizar o repositório" >&2
        exit 1
      fi
    fi

    # Navega até o diretório do projeto e constrói a imagem Docker
    cd /home/ubuntu/AWS
    if ! sudo docker build -t nhyira-api .; then
      echo "Falha ao construir a imagem Docker" >&2
      exit 1
    fi

    # Executa o Docker Compose para iniciar os serviços
    if ! sudo docker-compose -f /home/ubuntu/AWS/docker-compose.yml up -d; then
      echo "Falha ao executar o Docker Compose" >&2
      exit 1
    fi
  EOF
  )
}

