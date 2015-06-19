package ${bussiPackage}.entity;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import com.web.base.entity.BaseEntity;


/**   
 * @Title: Entity
 * @Description: ${ftl_description}
 * @author heqian
 * @date ${ftl_create_time}
 * @version V1.0   
 *
 */
@Entity
@Table(name = "${tableName}", schema = "")
@Inheritance(strategy = InheritanceType.JOINED)
@SuppressWarnings("serial")
public class ${entityName}Entity extends BaseEntity implements java.io.Serializable {
	<#list originalColumns as po>
	<#if po.isFk == '0'>
	/**${po.filedComment}*/
	private ${po.fieldType} ${po.fieldName};
	</#if>
	<#if po.isFk == '1'>
	/**${po.filedComment}*/
	private ${po.fkEntity} ${po.fkEntityFirstlower};
	</#if>
	</#list>
	
	<#list originalColumns as po>
	<#if po.isFk == '0'>
	/**
	 *方法: 取得${po.fieldName}
	 *@return: ${po.fieldType}  
	 *         ${po.filedComment}
	 */
	@Column(name ="${po.fieldDbName}")
	public ${po.fieldType} get${po.fieldName?cap_first}(){
		return this.${po.fieldName};
	}
	/**
	 *方法: 设置${po.fieldName}
	 *@param: ${po.fieldType}  ${po.filedComment}
	 */
	public void set${po.fieldName?cap_first}(${po.fieldType} ${po.fieldName}){
		this.${po.fieldName} = ${po.fieldName};
	}
	</#if>
	<#if po.isFk == '1'>
	/**
	 *方法: 取得${po.fieldName}
	 *@return: ${po.fieldType}  
	 *         ${po.filedComment}
	 */
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="${po.fieldDbName}")
	public ${po.fkEntity?cap_first} get${po.fkEntity?cap_first}(){
		return this.${po.fkEntityFirstlower};
	}
	/**
	 *方法: 设置${po.fieldName}
	 *@param: ${po.fieldType}  ${po.filedComment}
	 */
	public void set${po.fkEntity?cap_first}(${po.fkEntity?cap_first} ${po.fkEntityFirstlower}){
		this.${po.fkEntityFirstlower} = ${po.fieldName};
	}
	</#if>
	</#list>
}
