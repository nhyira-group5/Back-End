variable "az" {
  description = "Availability Zone"
  type        = string
  default     = "us-east-1a"
}

variable "key_pair_name" {
  description = "Key Pair Name"
  type        = string
  default     = "ti_key"
}

variable "ami" {
  description = "AMI ID"
  type        = string
  default     = "ami-0167aed34e622a91c"  # O ID da AMI 

}

variable "inst_type" {
  description = "Instance Type"
  type        = string
  default     = "t2.medium"
}

variable "subnet_id" {
  description = "Subnet ID"
  type        = string
  default     = "subnet-0e2701ab99c4d1b94"
}

variable "sg_id" {
  description = "Security Group ID"
  type        = string
  default     = "sg-05ad6fffec857ca5d"
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
