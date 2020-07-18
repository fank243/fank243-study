package com.fank243.springboot.admin;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fank243.springboot.admin.repository.SysPermissionRepository;
import com.fank243.springboot.admin.repository.SysRoleRepository;
import com.fank243.springboot.admin.repository.SysUserRepository;
import com.fank243.springboot.admin.shiro.ShiroUtils;
import com.fank243.springboot.core.entity.SysPermission;
import com.fank243.springboot.core.entity.SysRole;
import com.fank243.springboot.core.entity.SysUser;
import com.fank243.springboot.core.enums.PermissionType;
import com.fank243.springboot.core.enums.SysUserStatus;
import org.junit.Before;
import org.junit.Test;

import javax.annotation.Resource;
import java.io.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 数据初始化
 * 
 * @author FanWeiJie
 * @date 2020-03-24 14:18:54
 */
public class SystemInit extends BaseServiceTest {

    @Resource
    private SysPermissionRepository sysPermissionRepository;
    @Resource
    private SysRoleRepository sysRoleRepository;
    @Resource
    private SysUserRepository sysUserepository;

    private String jsonStr = "";

    @Before
    public void init() throws IOException {
        String jsonPath =
            "/home/jason/develop/workspace/idea/fank/springboot/fank243-admin/src/main/resources/data.json";
        File file = new File(jsonPath);
        if (!file.exists()) {
            System.exit(-1);
        }

        InputStream in = new FileInputStream(file);
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        int len;
        byte[] ch = new byte[128];
        while ((len = in.read(ch)) != -1) {
            out.write(ch, 0, len);
        }
        jsonStr = new String(out.toByteArray());
    }

    public void permission() {
        JSONObject jsonObject = JSONObject.parseObject(jsonStr);
        // 权限
        JSONArray jsonArray = jsonObject.getJSONArray("permission");
        for (Object obj : jsonArray) {
            JSONObject json = (JSONObject)obj;
            String name = json.getString("name");
            String type = json.getString("type");
            String perm = json.getString("permission");
            String uri = json.getString("uri");
            String icon = json.getString("icon");
            int sort = json.getInteger("sort");
            boolean external = json.getBoolean("external");
            boolean available = json.getBoolean("available");

            SysPermission permission = new SysPermission();
            permission.setName(name);
            permission.setPid(0L);
            permission.setType(PermissionType.getEnum(type));
            permission.setPermission(perm);
            permission.setUri(uri);
            permission.setIcon(icon);
            permission.setSort(sort);
            permission.setExternal(external);
            permission.setAvailable(available);

            permission = sysPermissionRepository.save(permission);
            if (permission.getId() == null || permission.getId() <= 0) {
                continue;
            }

            long pid = permission.getId();

            JSONArray children = json.getJSONArray("children");
            if (children == null || children.isEmpty()) {
                continue;
            }
            for (Object obj2 : children) {
                json = (JSONObject)obj2;
                name = json.getString("name");
                type = json.getString("type");
                perm = json.getString("permission");
                uri = json.getString("uri");
                icon = json.getString("icon");
                sort = json.getInteger("sort");
                external = json.getBoolean("external");
                available = json.getBoolean("available");

                permission = new SysPermission();
                permission.setName(name);
                permission.setPid(pid);
                permission.setType(PermissionType.getEnum(type));
                permission.setPermission(perm);
                permission.setUri(uri);
                permission.setIcon(icon);
                permission.setSort(sort);
                permission.setExternal(external);
                permission.setAvailable(available);

                sysPermissionRepository.save(permission);
            }
        }
    }

    public void role() {
        JSONObject jsonObject = JSONObject.parseObject(jsonStr);
        JSONArray jsonArray = jsonObject.getJSONArray("role");
        for (Object obj : jsonArray) {
            JSONObject json = (JSONObject)obj;
            String name = json.getString("name");
            String description = json.getString("description");
            boolean available = json.getBoolean("available");

            SysRole sysRole = new SysRole();
            sysRole.setName(name);
            sysRole.setDescription(description);
            sysRole.setAvailable(available);

            sysRoleRepository.save(sysRole);
        }
    }

    public void sysuser() {
        JSONObject jsonObject = JSONObject.parseObject(jsonStr);
        JSONArray jsonArray = jsonObject.getJSONArray("sysuser");
        for (Object obj : jsonArray) {
            JSONObject json = (JSONObject)obj;
            String username = json.getString("username");
            String realname = json.getString("realname");
            String mobile = json.getString("mobile");
            String wxNumber = json.getString("wxNumber");
            String salt = json.getString("salt");
            String password = json.getString("password");
            int status = json.getInteger("status");

            SysUser sysUser = new SysUser();
            sysUser.setUsername(username);
            sysUser.setRealname(realname);
            sysUser.setMobile(mobile);
            sysUser.setWxNumber(wxNumber);
            sysUser.setSalt(salt);
            sysUser.setPassword(ShiroUtils.md5WithSalt(salt, password));
            sysUser.setStatus(SysUserStatus.getEnum(status));

            sysUserepository.save(sysUser);
        }
    }

    public void sysuserRole() {
        List<SysUser> sysUserList = sysUserepository.findAll();
        SysUser sysUser = sysUserList.get(0);

        List<SysRole> sysRoleList = sysRoleRepository.findAll();
        Set<SysRole> sysRoleSet = new HashSet<>();
        sysRoleSet.add(sysRoleList.get(0));
        sysUser.setRoles(sysRoleSet);

        sysUserepository.save(sysUser);
    }

    public void rolePerm() {
        List<SysRole> sysRoleList = sysRoleRepository.findAll();
        SysRole sysRole = sysRoleList.get(0);

        List<SysPermission> sysPermissionList = sysPermissionRepository.findAll();
        Set<SysPermission> sysPermissionSet = new HashSet<>(sysPermissionList);
        sysRole.setPermissions(sysPermissionSet);

        sysRoleRepository.save(sysRole);
    }

    /** 主函数 **/
    @Test
    public void main() {
        permission();

        role();

        sysuser();

        sysuserRole();

        rolePerm();
    }
}
