package service.serviceImp;

import DAO.CompanyDao;
import DAO.UserDao;
import model.Company;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.CompanyService;
import service.UserService;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/7/19.
 */
@Service("companyService")
public class CompanyServiceImp implements CompanyService {

    @Autowired
    CompanyDao companyDao;

    public List<Company> findAll(Map<String,Object> map){
        return companyDao.findAll(map);
    };
    public int countAll(Map<String,Object> map){
        return companyDao.countAll(map);
    }
    public List<Company> findAllByPage(int page){
        return companyDao.findAllByPage(page);
    };
    public Company findOneById(Company company){
        return companyDao.findOneById(company);
    };
    public Company findOneByName(Company company){
        return companyDao.findOneByName(company);
    };
    public void addCompany(Company company){
        companyDao.addCompany(company);
    };
    public void updateCompany(Company company){
        companyDao.updateCompany(company);
    };
    public void deleteCompany(Company company){
        companyDao.deleteCompany(company);
    };
}
