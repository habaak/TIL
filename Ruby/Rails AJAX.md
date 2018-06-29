### Rails ajax 구현
#### 글 생성시 ajax 구현
1. data-remote = true  => html 태그 기준
```erb
<form action="/posts/<%= @post.id%>/comments" method="post" data-remote=true>
  <input type="text" name="content" /><br />
  <input type="hidden" name="authenticity_token" value="<%= form_authenticity_token %>">
  <input type="submit" />
</form>
```

2. CommentsController => create actions
```ruby
def create
  @comment = Post.find(params[:post_id]).comments.new(comment_params)

  if @comment.save
    respond_to do |format|         #요청에 따라 html 혹은 js 파일을 return
      format.html {redirect_to :back}
      format.js {render 'create_temp'}
    end
  else
    redirect_to :back
  end
end
```

3. create.js.erb 작성 (excape_javascript의 약어는 j)
```ruby
$("div#comment").append(`<p>  <%= escape_javascript(@comment.content) %>
  <%= escape_javascript( link_to '삭제하기', destroy_comment_path(@comment.id), method: :delete, remote: true, class: 'delete_comment') %>
</p><hr />`);
//backtick이나 escape_javascript는 자동으로 따음표들을 문자열 처리 시켜준다.
```
4. show.html.erb 파일에 ajax 결과에 따른 event handler 작성
```ruby
<script>
  $('form').on('ajax:success',function(){
    $('input[name="content"]').val('');
  });
</script>
```

#### 글 삭제시 ajax 구현
1. remote: true (rails 코드 기준)
```ruby
<%= comment.content %>
<%= link_to '삭제하기', destroy_comment_path(comment.id), remote: true, method: :delete, class: "delete_comment" %>
```
2. CommentsController => destroy action
```ruby
def destroy
  @comment = Comment.find(params[:comment_id])
  @comment.destroy
  respond_to do |format|
    format.html {redirect_to :back}
    format.js {}
  end
end
```
3. destroy.js.erb
```erb
var parnet = $('a[href="/comments/<%= params[:comment_id]%>"]').parent(); //p tag
var hr = parnet.next();
parnet.remove();
hr.remove();
```

### jQuery로 ajax 구현
#### 글 생성시 ajax 구현
1. script 생성
```erb
$('input[type="submit"]').on('click',function(e){
  e.preventDefault();
  alert('start');
  $.ajax({
    url: $('form').attr('action'),
    type: "POST",
    data: {content: $('input[name="content"]').val(),
        authenticity_token: $('[name="csrf-token"]').attr('content')},
    dataType: 'script',
    success: function(){
      alert('success');
      $('input[name="content"]').val('');
    },
    error: function(){
      alert('fail');
    }
  });
});
```
2. 이후 rails의 ajax 구현 순서와 동일

#### 글 삭제시 ajax 구현
1. script 생성
```erb
$('.delete_comment').on('click',function(e){
  e.preventDefault();
  $.ajax({
    url: this.href ,
    type: "DELETE",
    data: {authenticity_token: $('[name="csrf-token"]').attr('content')},
    dataType: 'script',
    success: function(){
      alert("delete_complete!")
    },
    error: function(){
      alert("delete_error!")
    }
  });
});
```
2. 이후 rails의 ajax 구현 순서와 동일
