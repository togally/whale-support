create database support_test1;
create database support_test2;
use support_test1;
CREATE TABLE test_user
(
    user_name varchar(255)
);

use support_test2;
CREATE TABLE test_user
(
    user_name varchar(255)
);

use support_test1;
insert into test_user(user_name) value ('张三');