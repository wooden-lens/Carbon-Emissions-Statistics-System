package com.example.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.entity.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 用户 REST 控制器
 * 遵循 RESTful 风格设计
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 获取所有用户
     * GET /api/users
     */
    @GetMapping
    public List<User> list() {
        return userService.listAll();
    }

    /**
     * 分页查询用户
     * GET /api/users/page?current=1&size=10
     */
    @GetMapping("/page")
    public Page<User> page(
            @RequestParam(defaultValue = "1") long current,
            @RequestParam(defaultValue = "10") long size) {
        return userService.page(new Page<>(current, size));
    }

    /**
     * 根据 ID 查询用户
     * GET /api/users/{id}
     */
    @GetMapping("/{id}")
    public User getById(@PathVariable Long id) {
        return userService.getById(id);
    }

    /**
     * 根据用户名查询用户
     * GET /api/users/username/{username}
     */
    @GetMapping("/username/{username}")
    public User getByUsername(@PathVariable String username) {
        return userService.getByUsername(username);
    }

    /**
     * 创建用户
     * POST /api/users
     * Body: {"username": "test", "email": "test@example.com"}
     */
    @PostMapping
    public User create(@RequestBody User user) {
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        userService.save(user);
        return user;
    }

    /**
     * 更新用户
     * PUT /api/users/{id}
     * Body: {"username": "updated", "email": "updated@example.com"}
     */
    @PutMapping("/{id}")
    public User update(@PathVariable Long id, @RequestBody User user) {
        user.setId(id);
        user.setUpdateTime(LocalDateTime.now());
        userService.updateById(user);
        return userService.getById(id);
    }

    /**
     * 删除用户
     * DELETE /api/users/{id}
     */
    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Long id) {
        return userService.removeById(id);
    }

    /**
     * 批量删除用户
     * DELETE /api/users/batch?ids=1,2,3
     */
    @DeleteMapping("/batch")
    public boolean batchDelete(@RequestParam List<Long> ids) {
        return userService.removeByIds(ids);
    }
}
