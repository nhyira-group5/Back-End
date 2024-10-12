variables.tf  do back end novo com instancia quase sem créditos
variable "az" {
  description = "Availability Zone"
  type        = string
  default     = "us-east-1a"
}

variable "key_pair_name" {
  description = "Key Pair Name"
  type        = string
  default     = "tf_key"
}

variable "ami" {
  description = "AMI ID"
  type        = string
  default     = "ami-048c8cb78e7408897"  # O ID da AMI 

}


variable "inst_type" {
  description = "Instance Type"
  type        = string
  default     = "t2.medium"
}

variable "subnet_id" {
  description = "Subnet ID"
  type        = string
  default     = "subnet-09424067824895155"
}

variable "sg_id" {
  description = "Security Group ID"
  type        = string
  default     = "sg-08a6c790338e94c72"
}

variable "dockerhub_username" {
  description = "Docker Hub username"
  type        = string
}

variable "snapshot_id" {
  default = "snap-0d20b4350b63ed2ee"
  description = "Snapshot ID Backend"
  type        = string
}
