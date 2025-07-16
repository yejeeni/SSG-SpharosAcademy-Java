package mall.notice.service;

import java.util.List;

import mall.notice.domain.Notice;

public interface NoticeService {
	public List<Notice> selectAll();
	
	public Notice select(int notice_id);

	public void regist(Notice notice);

	public void update(Notice notice);

	public void delete(int notice_id);
}