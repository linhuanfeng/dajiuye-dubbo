package com.lhf.dajiuye.user.service.strategy;

/**
 * 抽象策略角色
 */
public abstract class CheckTokenStrategy {

    public abstract boolean checkToken(String token);
}
