package cn.spider.colaDerive.domain.user;

/**
 * 论文子度量
 * PaperMetric
 *
 * @author Frank Zhang
 * @date 2018-09-20 3:26 PM
 */
public class PaperMetric extends SubMetric {

	public PaperMetric(){
		this.subMetricType = SubMetricType.Paper;
	}

	public PaperMetric(MainMetric parent) {
		this.parent = parent;
		parent.addSubMetric(this);
		this.subMetricType = SubMetricType.Paper;
	}

	@Override
	public double getWeight() {
		return parent.getMetricOwner().getWeight().getUnanimousWeight();
	}
}
