package mall.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import mall.domain.Product;
import mall.domain.ProductColor;
import mall.domain.ProductImg;
import mall.domain.ProductSize;
import mall.exception.ProductException;
import mall.repository.ProductColorDAO;
import mall.repository.ProductDAO;
import mall.repository.ProductImgDAO;
import mall.repository.ProductSizeDAO;
import mall.util.FileManager;

@Slf4j
@Service // 서비스는 모델 영역의 객체이긴 하지만, 직접 일하지 않고 주로 전담 객체들에게 일을 할당하는 역할
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDAO productDAO;
	@Autowired
	private ProductColorDAO productColorDAO;
	@Autowired
	private ProductSizeDAO productSizeDAO;
	@Autowired
	private ProductImgDAO productImgDAO;
	@Autowired
	private FileManager fileManager;

	/**
	 * 상품, 색상, 사이즈, 이미지, 파일 모두 등록
	 */
	@Override
	@Transactional
	public void regist(Product product, String savePath) throws ProductException {
		// 상품 등록 후 해당 상품의 pk 취득
		productDAO.insert(product);

		// 선택한 색상 수만큼 반복문으로 insert
		for (ProductColor productColor : product.getProductColorList()) {
			// 누락되어있던 product을 대입
			productColor.setProduct(product); // mybatis에 의해 pk가 채워진 product 대입
			productColorDAO.insert(productColor);
		}

		// 선택한 사이즈 수만큼 반복문으로 insert
		for (ProductSize productSize : product.getProductSizeList()) {
			// 누락되어있던 product을 대입
			productSize.setProduct(product); // mybatis에 의해 pk가 채워진 product 대입
			productSizeDAO.insert(productSize);
		}
		
		// 이미지 저장
		fileManager.save(product, savePath);
		
		// 이미지 파일명을 db에 저장
		for (ProductImg img : product.getProductImgList()) {
			img.setProduct(product);
			productImgDAO.insert(img);
		}
		

		// 색상 ID 추출
		List<Integer> colorIds = new ArrayList<>();
		for (ProductColor pc : product.getProductColorList()) {
			colorIds.add(pc.getColor().getColor_id());
		}

		// 사이즈 ID 추출
		List<Integer> sizeIds = new ArrayList<>();
		for (ProductSize ps : product.getProductSizeList()) {
			sizeIds.add(ps.getSize().getSize_id());
		}

		log.debug("[상품 등록] ID: {}, 색상: {}, 사이즈: {}, 이미지: {}", product.getProduct_id(), colorIds, sizeIds);
	}

}
