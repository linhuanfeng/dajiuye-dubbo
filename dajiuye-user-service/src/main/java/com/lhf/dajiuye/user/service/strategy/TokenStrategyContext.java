package com.lhf.dajiuye.user.service.strategy;

//@Component
public class TokenStrategyContext {
    private CheckTokenStrategy strategy;
    public TokenStrategyContext(CheckTokenStrategy strategy){
        this.strategy=strategy;
    }
    public boolean checkToken(String token){
        return strategy.checkToken(token);
    }
}
