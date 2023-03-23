/**
 * Copyright(C) 2018 Hangzhou Differsoft Co., Ltd. All rights reserved.
 */
package cn.spider;

/**
 * 类描述: RDS资产统计
 *
 * @since Jan 10, 2020 4:26:07 PM
 * @author yuzh
 *
 */
public class RdsPropertyInfo {

	/**
	 * 资产编号
	 */
	private Long rdsProcessNo;

	/**
	 * 实例ID
	 */
	private String dbInstanceId;
	
	/**
	 * 实例名字
	 */
	private String name;
	
	/**
	 * RDS平台(1 阿里云，2 聚石塔)
	 */
	private Integer platformType;
	
	/**
	 * 过期时间
	 */
	private String expireTime;
	
	/**
	 * 数据库类型
	 */
	private Integer engine;
	
	/**
	 * 数据库版本
	 */
	private String engineVersion;
	
	/**
	 * 数据库CPU
	 */
	private Integer dbInstanceCpu;
	
	/**
	 * 数据库内存
	 */
	private Long dbInstanceMemory;
	
	/**
	 * 数据库磁盘容量
	 */
	private Integer dbInstanceStorage;
	
	/**
	 * IOPS的最大使用量
	 */
	private Integer maxIops;
	
	/**
	 * 最大连接数
	 */
	private Integer maxConnection;

	/**
	 * @return the rdsProcessNo
	 */
	public Long getRdsProcessNo() {
		return rdsProcessNo;
	}

	/**
	 * @param rdsProcessNo 要设置的 rdsProcessNo
	 */
	public void setRdsProcessNo(Long rdsProcessNo) {
		this.rdsProcessNo = rdsProcessNo;
	}
	
	/**
	 * @return the dbInstanceId
	 */
	public String getDbInstanceId() {
		return dbInstanceId;
	}

	/**
	 * @param dbInstanceId 要设置的 dbInstanceId
	 */
	public void setDbInstanceId(String dbInstanceId) {
		this.dbInstanceId = dbInstanceId;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name 要设置的 name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the platformType
	 */
	public Integer getPlatformType() {
		return platformType;
	}

	/**
	 * @param platformType 要设置的 platformType
	 */
	public void setPlatformType(Integer platformType) {
		this.platformType = platformType;
	}
	
	/**
	 * @return the expireTime
	 */
	public String getExpireTime() {
		return expireTime;
	}

	/**
	 * @param expireTime 要设置的 expireTime
	 */
	public void setExpireTime(String expireTime) {
		this.expireTime = expireTime;
	}

	/**
	 * @return the engine
	 */
	public Integer getEngine() {
		return engine;
	}

	/**
	 * @param engine 要设置的 engine
	 */
	public void setengine(Integer engine) {
		this.engine = engine;
	}

	/**
	 * @return the engineVersion
	 */
	public String getEngineVersion() {
		return engineVersion;
	}

	/**
	 * @param engineVersion 要设置的 engineVersion
	 */
	public void setEngineVersion(String engineVersion) {
		this.engineVersion = engineVersion;
	}

	/**
	 * @return the dbInstanceCpu
	 */
	public Integer getDbInstanceCpu() {
		return dbInstanceCpu;
	}

	/**
	 * @param dbInstanceCpu 要设置的 dbInstanceCpu
	 */
	public void setDbInstanceCpu(Integer dbInstanceCpu) {
		this.dbInstanceCpu = dbInstanceCpu;
	}
	
	/**
	 * @return the dbInstanceMemory
	 */
	public Long getDbInstanceMemory() {
		return dbInstanceMemory;
	}

	/**
	 * @param dbInstanceMemory 要设置的 dbInstanceMemory
	 */
	public void setDbInstanceMemory(Long dbInstanceMemory) {
		this.dbInstanceMemory = dbInstanceMemory;
	}

	/**
	 * @return the dbInstanceStorage
	 */
	public Integer getDbInstanceStorage() {
		return dbInstanceStorage;
	}

	/**
	 * @param dbInstanceStorage 要设置的 dbInstanceStorage
	 */
	public void setDbInstanceStorage(Integer dbInstanceStorage) {
		this.dbInstanceStorage = dbInstanceStorage;
	}

	/**
	 * @return the maxIops
	 */
	public Integer getMaxIops() {
		return maxIops;
	}

	/**
	 * @param maxIops 要设置的 maxIops
	 */
	public void setMaxIops(Integer maxIops) {
		this.maxIops = maxIops;
	}

	/**
	 * @return the maxConnection
	 */
	public Integer getMaxConnection() {
		return maxConnection;
	}

	/**
	 * @param maxConnection 要设置的 maxConnection
	 */
	public void setMaxConnection(Integer maxConnection) {
		this.maxConnection = maxConnection;
	}
	
	/**
	 * 聚石塔基本信息的构造函数
	 * @param dbInstanceId
	 * @param name
	 * @param time
	 */
	public RdsPropertyInfo(Long rdsProcessNo, String dbInstanceId, String name, Integer platformType, Integer engine, String engineVersion, String time) {
		this.rdsProcessNo = rdsProcessNo;
		this.dbInstanceId = dbInstanceId;
		this.name = name;
		this.platformType = platformType;
		this.expireTime = time;
		this.engine = engine;
		this.engineVersion = engineVersion;
	}
	
	/**
	 * 聚石塔详细资产信息导入
	 * @param dbInstanceMemory
	 * @param dbInstanceStorage
	 * @param maxIops
	 * @param maxConnection
	 */
	public void setRdsDetailProperty(Integer dbInstanceCpu, Long dbInstanceMemory, Integer dbInstanceStorage, Integer maxIops, Integer maxConnection) {
		this.dbInstanceCpu = dbInstanceCpu;
		this.dbInstanceMemory = dbInstanceMemory;
		this.dbInstanceStorage = dbInstanceStorage;
		this.maxIops = maxIops;
		this.maxConnection = maxConnection;
	}

	public RdsPropertyInfo() {

	}

	@Override
	public String toString() {
		return "RdsPropertyInfo{" +
				"rdsProcessNo=" + rdsProcessNo +
				", dbInstanceId='" + dbInstanceId + '\'' +
				", name='" + name + '\'' +
				", platformType=" + platformType +
				", expireTime='" + expireTime + '\'' +
				", engine=" + engine +
				", engineVersion='" + engineVersion + '\'' +
				", dbInstanceCpu=" + dbInstanceCpu +
				", dbInstanceMemory=" + dbInstanceMemory +
				", dbInstanceStorage=" + dbInstanceStorage +
				", maxIops=" + maxIops +
				", maxConnection=" + maxConnection +
				'}';
	}
}
