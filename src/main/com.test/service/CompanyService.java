package service;

import model.Company;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/7/19.
 */
public interface CompanyService {
    public List<Company> findAll(Map<String,Object> map);
    public int countAll(Map<String,Object> map);
    public List<Company> findAllByPage(int page);
    public Company findOneById(Company company);
    public Company findOneByName(Company company);
    public void addCompany(Company company);
    public void updateCompany(Company company);
    public void deleteCompany(Company company);
}
