

drop user if exists 'addonis-user'@'localhost';

create user 'addonis-user'@'localhost' identified by 'addonis-password';

alter user 'addonis-user'@'localhost' account unlock;

grant all on addonis.* to 'addonis-user'@'localhost';

