#### Package in Atom (html)

- `autocomplet-html-entities` 
- `emmet` 
- `css-snippets`
- `autoclose-html`



### 사전 준비

##### - VirtualBox 5.3.0

##### - vagrant 1.9.5msi

##### - Git Bash



##### In gitBash

`$ cd  ##최상단 경로 `

`$ mkdir vagrant`

`$ cd vagrant ##vagrant dir로 이동`

`$ vagrant init`

##### In Vagrantfile(위 경로에 init 이후 생긴 파일)

```
# 수정 및 추가
Vagrant::DEFAULT_SERVER_URL.replace('https://vagrantcloud.com')
Vagrant.configure("2") do |config|
config.vm.box = "ubuntu/xenial64"
config.vm.network "forwarded_port", guest: 3000, host: 3000
end
    
```

##### In gitBash

`$ vagrant up`

`$ vagrant ssh  ## 위 설치가 완료된후 실행`

###### In ubunto *(In gitBash)    ##ruby  version `2.4.4` 설치

```cmd
$ curl -sL https://deb.nodesource.com/setup_8.x | sudo -E bash -
$ curl -sS https://dl.yarnpkg.com/debian/pubkey.gpg | sudo apt-key add -
$ echo "deb https://dl.yarnpkg.com/debian/ stable main" | sudo tee /etc/apt/sources.list.d/yarn.list

$ sudo apt-get update
$ sudo apt-get install git-core curl zlib1g-dev build-essential libssl-dev libreadline-dev libyaml-dev libsqlite3-dev sqlite3 libxml2-dev libxslt1-dev libcurl4-openssl-dev software-properties-common libffi-dev nodejs yarn
```

##### Rails Guide

- https://gorails.com/
- ruby  version `2.4.4`
- 