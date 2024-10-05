import tool from "@/utils/tool.js"

let BASE_URL = 'http://localhost:82/client/c'

let excludeFilterUrls = ['/user/login', '/user/register']

function http(param) {
	let url = param.url;
	let method = param.method;
	let header = param.header || {};
	let data = Object.assign(param.data || {});wx
	
	let showLoading = param.showLoading || false;

	// 判断是否登录
	let token = tool.data.get('TOKEN')
	if (token) {
		header['Token'] = token
	} else if (!excludeFilterUrls.includes(url)) {
		uni.navigateTo({
			url: '/pages/user/children/signin/signin'
		})
	}

	// 过滤属性为null的
	if(data){
		for (var key in data) {
			if (data[key] === null || data[key] === undefined || data[key] === "null") {
				delete data[key]
			}
		}
	}
	console.log(data);

	if (method) {
		if (method == "POST") {
			header = Object.assign({
				'content-type': "application/json;"
			}, header)
		} else {
			header = Object.assign({
				'content-type': "application/json;"
			}, header)
		}
	}

	if (showLoading) {
		uni.showLoading()
	}

	return new Promise((resolve, reject) => {
		uni.request({
			url: BASE_URL + url,
			data: data,
			method: method,
			header: header,
			success: (res) => {
				// 下面会直接写请求成功的代码
				if (showLoading) {
					uni.hideLoading();
				}
				if (res.data.code === 200) {
					resolve(res.data)
					return true
				} else {
					// 其他错误时，直接通过toast提示
					uni.showToast({
						title: res.data.msg,
						icon: 'none'
					})
					return false;
				}
			},
			fail: (err) => {
				// 下面会直接写请求失败的代码
				if (showLoading) {
					uni.hideLoading();
				}
				uni.showToast({
					icon: 'none',
					title: err
				})
				return false;
			},
		})
	})
}


export default http