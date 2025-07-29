<template>
	<view>
		<view class="text-xl text-center">{{heritage.title}}</view>
		<view class="divide margin-bottom-sm"></view>
		<rich-text :nodes="heritage.description"></rich-text>
	</view>
</template>

<script>
	import indexApi from "@/api/indexApi.js"
	
	export default {
		data() {
			return {
				heritage: {}
			};
		},
		onLoad(option) {
			indexApi.getheritagedetail({id: option.id}).then(res => {
				this.heritage = res.data
			})
		}
	}
</script>

<style lang="scss">
	page{
		background-color: #fff;
		padding: 14px;
	}
</style>
