package DAO;

import model.Company;
import model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/6/24.
 */
@Repository
public interface CompanyDao {

    public List<Company> findAll(Map<String,Object> map);
    public int countAll(Map<String,Object> map);
    public List<Company> findAllByPage(@Param("page") int page);
    public Company findOneById(Company company);
    public Company findOneByName(Company company);
    public void addCompany(Company company);
    public void updateCompany(Company company);
    public void deleteCompany(Company company);

}
