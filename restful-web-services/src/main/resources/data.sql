-- Lines of code below will execute automatically.
-- This will generate some data within the H2 database, and can be retrieved by calling 'userRepository.findAll()' in UserJPAController.java
insert into user values (10001, sysdate(), 'AB');
insert into user values (10002, sysdate(), 'CD');
insert into user values (10003, sysdate(), 'EF');
-- This creates a post with a specific user id
-- insert into post values (post-id, post-content, foreign-key-to-a-user)
insert into post values (11001, 'My first post', 10001);
insert into post values (11002, 'My 2nd post', 10001);