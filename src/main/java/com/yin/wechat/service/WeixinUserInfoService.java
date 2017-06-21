package com.yin.wechat.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.yin.wechat.Repository.WeixinUserInfoDao;
import com.yin.wechat.weixin.app.model.WeixinUserInfo;

@Service("weixinUserInfoService")
public class WeixinUserInfoService {
	@Autowired
	private WeixinUserInfoDao weixinUserInfoDao;

	public WeixinUserInfo saveUser(WeixinUserInfo weixinUserInfo)
	{
		return weixinUserInfoDao.save(weixinUserInfo);
	
	}
	public int refreshWeixinUserInfo(WeixinUserInfo weixinUserInfo)
	{
		return weixinUserInfoDao.refreshWeixinUserInfo(weixinUserInfo.getId(),
				weixinUserInfo.getSubscribe(),weixinUserInfo.getSubscribeTime(),
				weixinUserInfo.getNickname(),weixinUserInfo.getSex(),
				weixinUserInfo.getCountry(),weixinUserInfo.getProvince(),
				weixinUserInfo.getCity(),weixinUserInfo.getLanguage(),
				weixinUserInfo.getHeadImgUrl(),weixinUserInfo.getState(),
				weixinUserInfo.getSubscribeDate()
				);
	}
	public WeixinUserInfo getWeixinUserInfoById(Integer id){
		return weixinUserInfoDao.findOne(id);
	}
	public List<WeixinUserInfo> getallUser(){
		return weixinUserInfoDao.findAll();
	}
	public Page<WeixinUserInfo> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return weixinUserInfoDao.findAll(pageable);
	}
	public Page<WeixinUserInfo> pageUserByParam(final WeixinUserInfo weixinUserInfo,Pageable pageable) {
		Specification<WeixinUserInfo> specification = new Specification<WeixinUserInfo>() {
			@Override
			public Predicate toPredicate(Root<WeixinUserInfo> root,
					CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicates = new ArrayList<Predicate>();
				//参数非空判断。不为空则加此条件s
				if(weixinUserInfo.getNickname()!=null&&weixinUserInfo.getNickname().trim()!="")
				{
					Predicate payuser =
					criteriaBuilder.or(criteriaBuilder.like(root.get("openId"), "%"+weixinUserInfo.getNickname()+"%"),
							criteriaBuilder.like(root.get("nickname"), "%"+weixinUserInfo.getNickname()+"%"));
					predicates.add(payuser);
				}
				return criteriaBuilder.and(predicates.toArray(new Predicate[]{}));
			}
		};
		return weixinUserInfoDao.findAll(specification, pageable);
	}
	public WeixinUserInfo getWeixinUserInfoByopenId(String openId){
		return weixinUserInfoDao.getWeixinUserInfoByopenId(openId);
	}
	public int updateStateById(Integer id, String state) {
		// TODO Auto-generated method stub
		return weixinUserInfoDao.updateStateById(id,state);
	}
//	public List<WeixinUserInfo> findAll(String hotelName) {
//		QWeixinUserInfo cityEntity = QWeixinUserInfo.cityEntity;
//		JPAQuery<CityEntity>query = new JPAQuery<>(em);
//		BooleanExpression express = cityEntity.state.eq("1");
//		
//		if(StringUtils.hasText(hotelName)) {
//			express = express.and(cityEntity.hotels.any().name.likeIgnoreCase('%'+hotelName+'%'));
//		}
//		return query.select(cityEntity).from(cityEntity).where(express).fetch();
//	}
}
