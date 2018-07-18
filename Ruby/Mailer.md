### Mailer using Amazon SES (Simple Email Service)

1. [Amazon SES 접속](https://us-west-2.console.aws.amazon.com/ses/)
2. 이메일 인증 -> Verify
3. SMTP setting에서 `Create My SMTP Credentials`
  - id / pw는 다시 확인하기 어렵기 때문에 기록해둘것
4. [Devise에 confirmable 추가](https://github.com/plataformatec/devise/wiki/How-To:-Add-:confirmable-to-Users)
5. `rails g migration add_confirmable_to_devise` 명령어로 추가 추가하거나
```
# devise_create_user 파일 주석 해제
## Confirmable
t.string   :confirmation_token
t.datetime :confirmed_at
t.datetime :confirmation_sent_at
t.string   :unconfirmed_email # Only if using reconfirmable
```
