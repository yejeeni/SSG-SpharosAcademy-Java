package mall.notice.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import mall.notice.domain.Notice;
import mall.notice.repository.NoticeDAO;

@Slf4j
@Service
public class NoticeServiceImpl implements NoticeService {

//	@Qualifier("mybatisNoticeDAO") // 하이버네이트의 설정
	@Qualifier("hibernateNoticeDAO")
	@Autowired
	private NoticeDAO noticeDAO;

	@Override
	@Transactional
	public List<Notice> selectAll() {
//		log.debug("selectAll()");
		return noticeDAO.selectAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Notice select(int notice_id) {
		return noticeDAO.select(notice_id);
	}

	@Override
	@Transactional
	public void regist(Notice notice) {
		notice.setRegDate(new Date()); // 등록 시점 날짜 세팅. 도메인 객체의 생성자나 빌더에서 세팅하도록 개선하기
		noticeDAO.insert(notice);
	}

	@Override
	@Transactional
	public void update(Notice notice) {
		noticeDAO.update(notice);
	}

	@Override
	@Transactional
	public void delete(int notice_id) {
	    noticeDAO.delete(notice_id);
	}

}
