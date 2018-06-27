package com.accentrix.hku.service.applicant.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.Path;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.accentrix.hku.domain.applicant.Account;
import com.accentrix.hku.repository.applicant.AccountRepository;
import com.accentrix.hku.service.applicant.AccountService;
import com.accentrix.hku.util.UserUtils;
import com.accentrix.hku.vo.applicant.AccountVo;
import com.accentrix.hku.timer.annotation.Timer;
import com.eaio.uuid.UUID;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月31日 下午2:29:23
 * @version 1.0
 */

@Service
@Transactional(readOnly = true)
@Timer
@Path("account")
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public AccountVo get(String id) {
        Account account = accountRepository.findOne(id);
        return toVo(account);
    }

    @Transactional
    @Override
    public AccountVo save(AccountVo vo) {
        Account account = toAccount(vo);
        account = accountRepository.save(account);
        vo.setId(account.getId());
        return vo;
    }

    @Transactional
    @Override
    public List<AccountVo> save(List<AccountVo> vos) {
        List<AccountVo> accountVos = new ArrayList<AccountVo>();
        for (AccountVo accountVo : vos) {
            Account account = toAccount(accountVo);
            account = accountRepository.save(account);
            accountVo.setId(account.getId());
            accountVos.add(accountVo);
        }
        return accountVos;
    }

    @Transactional
    @Override
    public void delete(String id) {
        accountRepository.delete(id);
    }

    @Transactional
    @Override
    public void delete(AccountVo accountVo) {
        accountRepository.delete(toAccount(accountVo));
    }

    @Override
    public List<AccountVo> findList() {
        List<AccountVo> accountVos = new ArrayList<AccountVo>();
        List<Account> accounts = accountRepository.findAll();
        for (Account account : accounts) {
            accountVos.add(toVo(account));
        }
        return accountVos;
    }

    private Account toAccount(AccountVo vo) {
        Account account = new Account();
        account.setPersonEmail(vo.getPersonEmail());
        account.setPassword(UserUtils.entryptPassword(vo.getPassword()));
        account.setUserInfoId(vo.getUserInfoId());
        account.setLoginToken(vo.getLoginToken());
        account.setTimeOutDatetime(vo.getTimeOutDatetime());
        account.setRegDate(vo.getRegDate());
        account.setActivateCode(vo.getActivateCode());
        account.setActivateDate(vo.getActivateDate());
        return account;
    }

    private AccountVo toVo(Account account) {
        AccountVo vo = new AccountVo();
        vo.setId(account.getId());
        vo.setPersonEmail(account.getPersonEmail());
        vo.setPassword(account.getPassword());
        vo.setUserInfoId(account.getUserInfoId());
        vo.setLoginToken(account.getLoginToken());
        vo.setTimeOutDatetime(account.getTimeOutDatetime());
        vo.setRegDate(account.getRegDate());
        vo.setActivateCode(account.getActivateCode());
        vo.setActivateDate(account.getActivateDate());
        return vo;
    }

    @Transactional
    @Override
    public boolean activationAccount(String id) {
        id = UserUtils.decodeId(id);
        Account account = accountRepository.findOne(id);
        if (account != null) {
            account.setActivateCode(new UUID().toString().replace("-", ""));
            account.setActivateDate(new Date());
            accountRepository.save(account);
            return true;
        }
        return false;
    }

    @Override
    public AccountVo checkLogin(AccountVo vo) {
        if (vo != null) {
            AccountVo accountVo = new AccountVo();
            if (StringUtils.isNotBlank(vo.getPersonEmail())) {
                Account account = accountRepository.getByEmail(vo.getPersonEmail());
                if (null != account) {
                    accountVo = toVo(account);
                    if (StringUtils.isNotBlank(vo.getPassword())) {
                        boolean state = UserUtils.validatePassword(vo.getPassword(), accountVo.getPassword());
                        if (state) {
                            accountVo.setPassword("");
                            return accountVo;
                        }
                    }
                }
            }
        }
        return new AccountVo();
    }

    @Override
    public boolean checkEmail(String email) {
        Account account = accountRepository.checkEmail(email);
        if (account != null) {
            return true;
        }
        return false;
    }

    @Transactional
    @Override
    public void resetPassword(String email, String password) {
        email = UserUtils.decodeId(email);
        if (StringUtils.isNotBlank(email)) {
            Account account = accountRepository.getByEmail(email);
            if (account != null) {
                account.setPassword(UserUtils.entryptPassword(password));
                accountRepository.save(account);
            }
        }
    }

    @Override
    public boolean checkEmailForGotPassword(String email) {
        Account account = accountRepository.getByEmail(email);
        if (account != null) {
            return true;
        }
        return false;
    }

    @Override
    public boolean checkResetPasswordEmail(String email) {
        email = UserUtils.decodeId(email);
        if (StringUtils.isNotBlank(email)) {
            Account account = accountRepository.getByEmail(email);
            if (account != null) {
                return true;
            }
        }
        return false;
    }

    @Override
    public AccountVo getByApplicantInfoId(String applicantInfoId) {
        Account account = accountRepository.getByApplicantInfoId(applicantInfoId);
        return toVo(account);
    }
}
