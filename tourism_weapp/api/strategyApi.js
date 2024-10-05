import request from '@/utils/http.js'

export default {
	page(data) {
	  return request({
	    url: '/wxstrategy/getstrategy',
	    method: 'GET',
	    data: data,
			showLoading: true,
	  })
	},
	 
	detail(data) {
	  return request({
	    url: '/wxstrategy/getstrategyinfo',
	    method: 'GET',
	    data: data,
			showLoading: true,
	  })
	},
	
	like(data) {
	  return request({
	    url: '/wxstrategy/like',
	    method: 'GET',
	    data: data,
			showLoading: true,
	  })
	},
	
	isuserlike(data){
		return request({
		  url: '/wxstrategy/isuserlike',
		  method: 'GET',
		  data: data,
			showLoading: true,
		})
	}
	
}




