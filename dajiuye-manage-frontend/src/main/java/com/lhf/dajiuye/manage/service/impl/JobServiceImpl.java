package com.lhf.dajiuye.manage.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.lhf.dajiuye.manage.dao.Jobdao;
import com.lhf.dajiuye.manage.entiity.Job;
import com.lhf.dajiuye.manage.service.JobService;

@Service
public class JobServiceImpl extends ServiceImpl<Jobdao, Job> implements JobService {
}
