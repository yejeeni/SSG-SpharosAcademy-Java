package mall.notice.repository;

import java.util.List;

import mall.notice.domain.Notice;

/**
 * DAO에 관계없이 Notice를 대상으로 한 DAO의 최상위 DAO
 */
public interface NoticeDAO {
	public List selectAll();

	public Notice select(int notice_id);

	public void insert(Notice notice);

	public void update(Notice notice);

	public void delete(int notice_id);
}
