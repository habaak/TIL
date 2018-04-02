exports.success = {isSuccess : 1};

exports.login = 'select email from users where email=? and pw=?';

exports.join = 'insert into users(email, name, pw, phone) values (?,?,?,?)';

exports.user_info = 'select name, phone from users where email=?';

exports.info_modify = 'update users set name=?, phone=? where email=?';

exports.pw_modify = 'update users set pw=? where email=?';

exports.main ='	select tp.tnum,tp.tname, tp.thumbnail, tp.photo from town_photo tp, town_review tr where tp.tnum = tr.tnum group by tp.tnum order by tr.rcount desc limit 3';

exports.city_list = 'select c.cnum, c.cname, c.cphoto, c.cthumbnail ,count(t.tnum) as count_town from city c left outer join town t on c.cnum = t.cnum group by c.cnum';

exports.city_dropbar = 'select t.tnum, t.tname, t.detail_add, attr.photo, attr.thumbnail, tr.rcount  from town t, city c, town_review tr, attraction attr where c.cname=? and c.cnum = t.cnum and t.tnum = tr.tnum and t.tnum = attr.tnum and date_format(now(), "%yyyy-%mm-%dd") <= date_format(t.start_day, "%yyyy-%mm-%dd") and t.people > t.join_people';

exports.town_list = 'select tr.tnum, tr.tname, tp.photo,tp.thumbnail, tr.rcount from town_review tr, town_photo tp where tr.tnum = tp.tnum and date_format(now(), "%yyyy-%mm-%dd") <= date_format(tp.start_day, "%yyyy-%mm-%dd") and tp.people > tp.join_people group by tr.tnum';

exports.town_name_info = 'select t.tnum, t.tname, t.detail_add, DATE_FORMAT(t.start_day, "%Y-%m-%d") as start_day, DATE_FORMAT(t.end_day, "%Y-%m-%d") as end_day, t.phone, c.cname from city c, town t where t.tname=? and c.cnum = t.cnum and date_format(now(), "%y-%m-%d") <= date_format(t.start_day, "%y-%m-%d") and t.people > t.join_people';

exports.name_attr_content ='select attr.num, attr.photo, attr.thumbnail, attr.content from town t, attraction attr where t.tname=? and t.tnum = attr.tnum and date_format(now(), "%y-%m-%d") <= date_format(t.start_day, "%y-%m-%d") and t.people > t.join_people';

exports.search_key = 'select t.tnum, t.tname, t.detail_add, attr.thumbnail, c.cname from city c, town t, attraction attr where t.tname like "%"?"%" and t.cnum = c.cnum and t.tnum = attr.tnum and date_format(now(), "%y-%m-%d") <= date_format(t.start_day, "%y-%m-%d") and t.people > t.join_people group by t.tnum';

exports.town_tnum_info='select t.tnum, t.tname, t.detail_add, DATE_FORMAT(t.start_day, "%Y-%m-%d") as start_day, DATE_FORMAT(t.end_day, "%Y-%m-%d") as end_day, t.people, t.phone, c.cname from town t, city c where tnum = ? and c.cnum = t.cnum';

exports.tnum_attr_content = 'select attr.num, attr.photo, attr.thumbnail, attr.content from town t, attraction attr where t.tnum = ? and t.tnum = attr.tnum';

exports.my_town = 'select num, c.cname, t.tname, t.detail_add, b.people_num, b.confirm from booking b, city c, town t where b.email=? and date_format(now(), "%yyyy-%mm-%dd") <= date_format(b.start_day, "%yyyy-%mm-%dd") and c.cnum = t.cnum and t.tnum = b.tnum';

exports.update_join_people = 'update town set join_people = join_people - (select people_num from booking where num = ?) where tnum = (select tnum from booking where num = ?) ';

exports.delete_booking = 'delete from booking where num=?';

exports.tnum_town_info = 'select t.tnum, t.tname, c.cname, t.detail_add, t.phone, date_format(t.start_day, "%y-%m-%d") as start_day, date_format(t.end_day, "%y-%m-%d") as end_day from city c, town t where c.cnum = t.cnum and t.tnum=?';

exports.tnum_town_attr = 'select attr.thumbnail, attr.photo, attr.content from town t, attraction attr where t.tnum = attr.tnum and t.tnum = ?';

exports.tnum_town_eating = 'select eat.thumbnail, eat.photo, eat.content from town t, eating eat where t.tnum = eat.tnum and t.tnum = ?';

exports.tnum_town_ex = 'select ex.thumbnail, ex.photo, ex.content from town t, experience ex where t.tnum = ex.tnum and t.tnum = ?';

exports.tnum_review = 'select r.num, u.email, r.content, date_format(r.regdate, "%y-%m-%d") as regdate from users u, review r  where r.tnum = ? and r.email = u.email';

exports.show_review = 'select r.num, r.email, u.name, r.content, date_format(r.regdate, "%y-%m-%d") as regdate from review r, users u where tnum = ? and r.email = u.email';

exports.review_auth = 'select email from booking where email=? and tnum=? and confirm=2';

exports.review_write = 'insert into review(email, tnum, content) values (?,?,?)';

exports.reserve = 'insert into booking(email, tnum, people_num, start_day, end_day) values (?,?, ?, date_format(?, "%y-%m-%d"), date_format(?, "%y-%m-%d"))';

exports.reserve_join_people = 'update town set join_people = join_people + ? where tnum = ?';