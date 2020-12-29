package com.oes.service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.oes.Exceptions.database.DBOperateException;
import com.oes.Exceptions.organization.OrgLicenseExistsException;
import com.oes.Exceptions.organization.OrgNotExistsException;
import com.oes.constant.enums.BusinessType;
import com.oes.dao.OrganizationWorkersDao;
import com.oes.dao.OrganizationsDao;
import com.oes.dao.RolesDao;
import com.oes.model.dto.BaseResultDTO;
import com.oes.model.dto.OrganizationTeacherDTO;
import com.oes.model.entity.AuthenticatedUser;
import com.oes.model.entity.Organization;
import com.oes.model.entity.OrganizationWorker;
import com.oes.model.entity.Role;
import com.oes.model.enums.RegistState;
import com.oes.model.example.OrganizationExample;
import com.oes.model.query.OrganizationQuery;
import com.oes.util.http.HttpResult;
import com.oes.validator.ValidationResult;
import com.oes.vo.OrganizationInfoVO;
import com.oes.vo.OrganizationTeacherVO;
import com.oes.vo.OrganizationVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
//import org.hibernate.validator.internal.enumsgine.ValidatorImpl;
import com.oes.validator.ValidatorImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
@Slf4j
public class OrganizationsService {

    @Value("${server.base-url}")
    private String baseUrl;

    @Resource
    private OrganizationsDao organizationsDao;



    @Resource
    private OrganizationWorkersDao workersDao;

    @Resource
    private RolesDao rolesDao;



    @Resource
    private ValidatorImpl validator;

    /**
     * 根据员工id查询是否有机构注册
     */
    public BaseResultDTO getRegisterStateByUserId(long userId) {
        HttpResult result = new HttpResult();
        OrganizationWorker Admin = null;
        Role total_admin = rolesDao.queryByName("total_Admin");
        Admin = workersDao.queryById(userId, total_admin.getRoleid());
        if (Admin != null) {
            return new BaseResultDTO(BusinessType.SELECT, true, "该用户已注册机构", Admin.getOrganizationId());
        } else {
            return new BaseResultDTO(BusinessType.SELECT, false, "该用户尚未注册机构");
        }
    }

    /**
     *根据name查询机构信息（支持模糊查询）
     */
    public List<Organization> queryByName(String name) {
        List<Organization> organizations = organizationsDao.queryByKeyword(name);

        if (Objects.nonNull(organizations)) {
            return organizations;
        } else {
            throw new DBOperateException("数据库查询失败");
        }
    }

    /**
     * 机构信息录入
     */
    public int addOrganization(Organization organization) {
        OrganizationExample example = new OrganizationExample();
        OrganizationExample.Criteria criteria = example.createCriteria();
        criteria.andLicenseNumberEqualTo(organization.getLicenseNumber());
        if (organizationsDao.countByExample(example) > 0) {
            throw new OrgLicenseExistsException("营业执照编号已存在");
        }
        organization.setRegisterState(RegistState.unChecked);
        int row = 0;
        try {
            row = organizationsDao.insert(organization);
        } catch(Exception e) {
            log.error("机构信息录入失败，机构信息：{}", organization, e);
            throw new DBOperateException("数据存储失败");
        }
        return row;
    }

    /**
     * 查看机构注册进度
     */
    public BaseResultDTO getRegisterProgress(long orgId) {
        BaseResultDTO resultDTO = new BaseResultDTO();
        Organization organization = organizationsDao.queryById(orgId);
        if (Objects.isNull(organization)) {
            resultDTO.setSuccess(false);
            resultDTO.setMessage("没有查询到该机构的注册信息");
            return resultDTO;
        }
        RegistState registerState = organization.getRegisterState();
        JSONObject object = new JSONObject();
        object.put("progress", registerState.getValue());

        if (registerState == RegistState.CheckedFailed) {
            object.put("fail_reason", organization.getFail_reason());
        }

        resultDTO.setSuccess(true);
        resultDTO.setMessage(registerState.getDescription());
        resultDTO.setData(object);
        return resultDTO;
    }

    /**
     * 注销机构
     */
    public int deleteOrg(long orgId) {
        int i = organizationsDao.updateRegisterState(RegistState.writtenOff, orgId);
//        Organization organization = organizationsDao.queryById(orgId);
//        organization.setRegisterState(RegistState.writtenOff);
//        int row = organizationsDao.update(organization);
        return i;
    }


    public int updateOrganization(Organization organization) {
//      Organization organization1 = organizationsDao.queryById(organization.getOrgId());
//      organization.setRegisterState(organization1.getRegisterState());
        int row = organizationsDao.update(organization);
        if (row > 0) {
            return row;
        } else {
            throw new DBOperateException("数据库操作错误");
        }
    }

/*    public byte[] getOrgQRCode(Long orgId) {
        Organization organization = organizationsDao.queryById(orgId);
        if (Objects.isNull(organization)) {
            throw new DBOperateException("确定是否有该机构信息");
        }
        Picture picture = picturesDao.queryById(organization.getQrcode());
        if (picture != null)
            return picture.getPictureData();
        else
            throw new DBOperateException("二维码获取失败");
    }*/

    /**
     * 根据id查询机构信息
     */
    public OrganizationVo getOrgInfoById(Long orgId) {
        Organization organization = organizationsDao.queryById(orgId);
        if (organization == null)
            throw new OrgNotExistsException("该机构不存在");
        OrganizationVo organizationVo = new OrganizationVo();
        BeanUtils.copyProperties(organization, organizationVo);
        if (organization.getQrcode() != 0) {
            String QrCodeUrl = baseUrl + organization.getQrcode();
            organizationVo.setQrcode(QrCodeUrl);
        }
        if (organization.getLicensePhoto() != 0) {
            String licensePhotoUrl = baseUrl + organization.getLicensePhoto();
            organizationVo.setLicensePhoto(licensePhotoUrl);
        }
        if (organization.getBankAccountPhoto() != 0) {
            String bankAccountPhotoUrl = baseUrl + organization.getBankAccountPhoto();
            organizationVo.setBankAccountPhoto(bankAccountPhotoUrl);
        }
        if (organization.getQualificationPhoto() != 0) {
            String qualificationPhotoUrl = baseUrl + organization.getQualificationPhoto();
            organizationVo.setQualificationPhoto(qualificationPhotoUrl);
        }
        if (organization.getAdverPhoto() != 0) {
            String adverPhotoUrl = baseUrl + organization.getAdverPhoto();
            organizationVo.setAdverPhoto(adverPhotoUrl);
        }
        return organizationVo;
    }

    /**
     * 机构添加到归档文件
     * @param orgId
     * @return
     */
    public int addInfoToLogOff(Long orgId) {
        if(orgId <= 0){
            throw new IllegalArgumentException("机构编号大于0");
        }
        Organization organization = organizationsDao.queryById(orgId);
        if(organization == null){
            throw new OrgNotExistsException("机构不存在");
        }
        organization.setRegisterState(RegistState.writtenOff);
        return organizationsDao.insertLogOff(organization);
    }

    public List<OrganizationVo> getOrganizations(OrganizationQuery query) {
        ValidationResult validationResult =  validator.validate(query);
        if(validationResult.isHasErrors()){
            throw new IllegalArgumentException(validationResult.getErrMsg());
        }
        List<Organization> organizations = organizationsDao.queryOrganizations(query);
        List<OrganizationVo> organizationVos = new ArrayList<>();
        for (Organization organization : organizations) {
            OrganizationVo organizationVo = new OrganizationVo();
            BeanUtils.copyProperties(organization, organizationVo);
            if (organization.getQrcode() != 0) {
                String QrCodeUrl = baseUrl + organization.getQrcode();
                organizationVo.setQrcode(QrCodeUrl);
            }
            if (organization.getLicensePhoto() != 0) {
                String licensePhotoUrl = baseUrl + organization.getLicensePhoto();
                organizationVo.setLicensePhoto(licensePhotoUrl);
            }
            if (organization.getBankAccountPhoto() != 0) {
                String bankAccountPhotoUrl = baseUrl + organization.getBankAccountPhoto();
                organizationVo.setBankAccountPhoto(bankAccountPhotoUrl);
            }
            if (organization.getQualificationPhoto() != 0) {
                String qualificationPhotoUrl = baseUrl + organization.getQualificationPhoto();
                organizationVo.setQualificationPhoto(qualificationPhotoUrl);
            }
            if (organization.getAdverPhoto() != 0) {
                String adverPhotoUrl = baseUrl + organization.getAdverPhoto();
                organizationVo.setAdverPhoto(adverPhotoUrl);
            }
            organizationVos.add(organizationVo);
        }
        return organizationVos;
    }

    public BaseResultDTO getOrganizationsByState(OrganizationQuery query) {
        ValidationResult validationResult = validator.validate(query);
        if(validationResult.isHasErrors()){
            throw new IllegalArgumentException(validationResult.getErrMsg());
        }
        PageHelper.startPage(query.getPageNum(),query.getPageSize());
        List<Organization> organizations = organizationsDao.queryByStateQuery(query);
        PageInfo<Organization> pageInfo = new PageInfo<>(organizations);
        PageInfo<OrganizationInfoVO> result = new PageInfo<>();
        BeanUtils.copyProperties(pageInfo, result);
        List<OrganizationInfoVO> vos = new ArrayList<>();
        if(!Objects.isNull(organizations)) {
            for (Organization organization : organizations) {
                OrganizationInfoVO vo = new OrganizationInfoVO();
                BeanUtils.copyProperties(organization,vo);
                StringBuilder orgAddress = new StringBuilder();
                if(!StringUtils.isBlank(organization.getProvince())){
                    orgAddress.append(organization.getProvince());
                }
                if(!StringUtils.isBlank(organization.getCity())){
                    orgAddress.append(organization.getCity());
                }
                if(!StringUtils.isBlank(organization.getDistrict())){
                    orgAddress.append(organization.getDistrict());
                }
                if(!StringUtils.isBlank(organization.getAddress())){
                    orgAddress.append(organization.getAddress());
                }
                vo.setOrgAddress(orgAddress.toString());
                vos.add(vo);
            }
        }
        result.setList(vos);
        return new BaseResultDTO(true, result);
    }

    public BaseResultDTO getOrganizationTeacher() {
        List<OrganizationTeacherDTO> organizationTeacher = organizationsDao.getOrganizationTeacher();
        List<OrganizationTeacherVO> vos = new ArrayList<>();
        for (OrganizationTeacherDTO ot : organizationTeacher) {
            OrganizationTeacherVO vo = new OrganizationTeacherVO();
            BeanUtils.copyProperties(ot, vo);
            List<AuthenticatedUser> authenticatedUsers = ot.getChildren();
            List<Map<String, Object>> teachers = new ArrayList<>();
            for (AuthenticatedUser teacher : authenticatedUsers) {
                Map<String, Object> map = new HashMap<>();
                if(teacher.getUserid()!=null && teacher.getName()!=null) {
                    map.put("value", teacher.getUserid());
                    map.put("label", teacher.getName());
                    teachers.add(map);
                }
            }
            vo.setChildren(teachers);
            vos.add(vo);
        }
        return new BaseResultDTO(true, vos);
    }
}