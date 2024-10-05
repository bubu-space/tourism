export default {
	data: {
		set(key,value){
			uni.setStorageSync(key,value)
		},
		get(key){
			return uni.getStorageSync(key)
		},
		remove(key){
			uni.removeStorageSync(key)
		},
		clear(){
			uni.clearStorage()
		}
	}
}