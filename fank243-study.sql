drop table if exists tb_file;
create table tb_file
(
    file_id            bigint primary key not null comment '文件ID',
    file_name          varchar(100)       not null default '' comment '文件名称',
    mine_type          varchar(100)       not null default '' comment 'MineType',
    file_type          varchar(50)        not null default '' comment '文件类型',
    file_prefix        varchar(50)        not null default '' comment '文件前缀',
    file_path          varchar(200)       not null default '' comment '文件相对路径',
    file_size          bigint             not null default 0 comment '文件大小(byte)',
    file_md5           varchar(32)        not null default '' comment '文件MD5签名',
    created_by         varchar(32)        not null default '' comment '创建人',
    created_date       datetime           not null comment '创建时间',
    last_modified_by   varchar(32)        not null default '' comment '最近修改人',
    last_modified_date datetime           not null comment '最近修改时间'
)
    comment '上传文件表';

drop table if exists tb_oauth_client;
create table tb_oauth_client
(
    client_id              varchar(128) primary key not null,
    client_secret          varchar(256)             not null default '' comment '客户端密钥',
    resource_ids           varchar(256)             not null default '' comment '资源ID集合(英文逗号分割)',
    scope                  varchar(256)             not null default '' comment '授权范围(英文逗号分割)',
    grant_types            varchar(256)             not null default '' comment '授权类型(英文逗号分割)',
    redirect_url           varchar(256)             not null default '' comment '重定向地址',
    additional_information varchar(4096)            not null default '' comment '额外信息'
) comment 'Oauth2认证客户端表';


drop table if exists tb_oauth_access_token;
create table tb_oauth_access_token
(
    token_id varchar(40) primary key not null comment '令牌ID',
    user_id  bigint                  null comment '用户ID',
    open_id  varchar(64)             not null default '' comment 'OpenID'
)
    comment '用户授权令牌表';
drop table if exists tb_oauth_user;

drop table if exists tb_oauth_user;
create table tb_oauth_user
(
    user_id            bigint primary key not null comment '用户ID',
    username           varchar(20)        not null default '' comment '用户名',
    mobile             varchar(11)        not null default '' comment '手机号码',
    nickname           varchar(20)        not null default '' comment '昵称',
    password           varchar(40)        not null default '' comment '登录密码',
    status             tinyint            not null default 0 comment '用户状态(0：正常，1：登录锁定，2：已禁用)',
    login_err_count    int                not null default 0 comment '连续登录错误次数',
    login_lock_time    datetime           null comment '登录锁定时间',
    last_login_time    datetime           null comment '最后登录时间',
    last_login_ip      varchar(64)        not null default '' comment '最后登录IP',
    is_deleted         tinyint unsigned   not null default 0 comment '是否已删除(0：否，1：是)',
    created_date       datetime           not null comment '创建时间',
    last_modified_date datetime           not null comment '最近修改时间'
) comment 'Oauth2用户表';

drop table if exists tb_support_area;
create table tb_support_area
(
    area_id            bigint primary key not null comment '区域ID',
    province_code      varchar(6)         not null default '' comment '区划代码(省级)',
    province_name      varchar(30)        not null default '' comment '区划名称(省级)',
    city_code          varchar(6)         not null default '' comment '区划代码(市级)',
    city_name          varchar(30)        not null default '' comment '区划名称(市级)',
    area_code          varchar(6)         not null default '' comment '区划代码(县级)',
    area_name          varchar(30)        not null default '' comment '区划名称(县级)',
    created_by         varchar(32)        not null default '' comment '创建人',
    created_date       datetime           not null comment '创建时间',
    last_modified_by   varchar(32)        not null comment '最近修改人',
    last_modified_date datetime           not null comment '最近修改时间'
) comment '国家行政区划表';

drop table if exists tb_operation_log;
create table tb_operation_log
(
    log_id       bigint primary key not null comment '日志ID',
    tenant       varchar(32)        not null default '' comment '租户',
    log_type     varchar(20)        not null default '' comment '保存的操作日志的类型',
    biz_no       varchar(200)       not null default '' comment '日志绑定的业务标识',
    sub_type     varchar(20)        not null default '' comment '日志的子类型',
    operator_id  varchar(32)        not null default '' comment '操作人ID',
    content      varchar(200)       not null default '' comment '日志内容',
    extra        varchar(500)       not null default '' comment '扩展信息',
    created_date datetime           not null comment '创建时间'
) comment '业务日志记录表';

drop table if exists tb_support_sms;
create table tb_support_sms
(
    sms_id       bigint primary key not null comment '短信ID',
    msg_id       varchar(32)        not null default '' comment '消息ID',
    mobile       varchar(11)        not null default '' comment '接受手机号码',
    content      text               not null comment '短信内容',
    created_date datetime           not null comment '创建时间'
) comment '短信发送记录表';

drop table if exists tb_sys_user;
create table tb_sys_user
(
    user_id            bigint primary key not null comment '用户ID',
    username           varchar(20)        not null default '' comment '用户名',
    nickname           varchar(20)        not null default '' comment '昵称',
    status             tinyint            not null default 0 comment '用户状态(0：正常，1：已禁用)',
    open_id            varchar(64)        not null default '' comment 'OpenID',
    is_deleted         tinyint unsigned   not null default 0 comment '是否已删除(0：否，1：是)',
    created_by         varchar(40)        not null default '' comment '创建人',
    created_date       datetime           not null comment '创建时间',
    last_modified_by   varchar(40)        not null default '' comment '最近修改人',
    last_modified_date datetime           not null comment '最近修改时间'

) comment '系统管理员表';

drop table if exists tb_sys_role;
create table tb_sys_role
(
    role_id            bigint           not null primary key comment '角色ID',
    role_code          varchar(20)      not null default '' comment '角色代码',
    role_name          varchar(40)      not null default '' comment '角色名称',
    role_desc          varchar(200)     not null default '' comment '角色描述',
    status             tinyint(1)       not null default 0 comment '角色状态(0：正常，1：禁用)',
    is_deleted         tinyint unsigned not null default 0 comment '是否已删除(0：否，1：是)',
    created_by         varchar(40)      not null default '' comment '创建人',
    created_date       datetime         not null comment '创建时间',
    last_modified_by   varchar(40)      not null default '' comment '最近修改人',
    last_modified_date datetime         not null comment '最近修改时间'
) comment '系统角色表';

drop table if exists tb_sys_perm;
create table tb_sys_perm
(
    perm_id            bigint       not null primary key comment '权限ID',
    perm_pid           bigint       null comment '权限父ID',
    perm_code          varchar(20)  not null default '' comment '权限代码',
    perm_uri           varchar(200) not null default '' comment '权限路径',
    perm_name          varchar(20)  not null default '' comment '权限名称',
    perm_desc          varchar(200) not null default '' comment '权限描述',
    perm_type          tinyint(1)   not null default 0 comment '权限类型(0：目录，1：菜单，2：接口)',
    is_external        tinyint(1)   not null default 0 comment '是否外部链接(0：否，1：是)',
    status             tinyint(1)   not null default 0 comment '权限状态(0：正常，1：禁用)',
    created_by         varchar(40)  not null default '' comment '创建人',
    created_date       datetime     not null comment '创建时间',
    last_modified_by   varchar(40)  not null default '' comment '最近修改人',
    last_modified_date datetime     not null comment '最近修改时间'
) comment '系统权限表';


drop table if exists tb_sys_user_role;
create table tb_sys_user_role
(
    id      bigint primary key not null comment '主键ID',
    user_id bigint             not null comment '用户ID',
    role_id bigint             not null comment '角色ID'
) comment '系统用户角色关联表';

drop table if exists tb_sys_role_perm;
create table tb_sys_role_perm
(
    id      bigint primary key not null comment '主键ID',
    role_id bigint             not null comment '角色ID',
    perm_id bigint             not null comment '权限ID'
) comment '系统角色权限关联表';

drop table if exists tb_sys_user_login_log;
create table tb_sys_user_login_log
(
    id           bigint primary key not null comment '主键ID',
    user_id      bigint             not null comment '用户ID',
    login_time   datetime           not null comment '登录时间',
    login_ip     varchar(64)        not null default '' comment '登录IP',
    login_device varchar(200)       not null default '' comment '登录设备'
) comment '系统管理员登录日志表';





















