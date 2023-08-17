-- @spring
create table post (
    id number,
    title varchar2(500),
    writer varchar2(50),
    content varchar2(4000),
    created_at date default sysdate,
    constraints pk_post_id primary key (id)
);

create sequence seq_post_id;
desc post;

-- POST /posts
insert into post (id, title, writer, content, created_at) values (seq_post_id.nextval, 'AI는 무엇인가요?', 'honggd', 'Artificial intelligence (AI) is the intelligence of machines or software, as opposed to the intelligence of human beings or animals. AI applications include advanced web search engines (e.g., Google Search), recommendation systems (used by YouTube, Amazon, and Netflix), understanding human speech (such as Siri and Alexa), self-driving cars (e.g., Waymo), generative or creative tools (ChatGPT and AI art), and competing at the highest level in strategic games (such as chess and Go).[1]', default);
insert into post (id, title, writer, content, created_at) values (seq_post_id.nextval, 'CHAT-GPT', 'honggd', 'ChatGPT is a large language model-based chatbot developed by OpenAI.', default);
insert into post (id, title, writer, content, created_at) values (seq_post_id.nextval, 'React', 'sejong', '"React" is a popular JavaScript library for building user interfaces. React was developed by Facebook and has become widely used in web development.', default);

-- GET /posts
select * from post order by id desc;
    
-- GET /posts/1
select * from post where id = 1;

-- PATCH /posts/1
update post set title = '짱 쉬운 AI', content = 'Artificial intelligence, That\'s all~.' escape '\' where id = 1;

-- DELETE /posts/1
delete from post where id = 1;




