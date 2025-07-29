<template>
	<view>
		<text class="h2">{{news.title}}</text>
		<view class="flex justify-between">
			<text class="weaktext">浏览量：{{news.viewCount||0}}</text>
			<text class="weaktext">发布时间：{{news.createTime}}</text>
		</view>
		
		<view class="divide" style="margin-bottom: 10px;"></view>
		
		<rich-text :nodes="news.content"></rich-text>
		
		<view class="share">
			<button open-type="share">
				<text class="cuIcon-share icon"></text>
				<text>分享</text>
			</button>
		</view>
	</view>
</template>

<script>
	import indexApi from "@/api/indexApi.js"
	
	export default {
		data() {
			return {
				news: {}
			}
		},
		onLoad(option) {
			let param = {
				id: option.id
			} 
			indexApi.getactiviteinfo(param).then(res => {
				this.news = res.data
			})
		},
		methods: {
			
		}
	}
</script>


<style lang="scss">
	page{
		background-color: #fff!important;
		padding: 12px!important;
	}
	
	.share{
		position: fixed;
		bottom: 100px;
		right: 10px;
			
		button{
			display: flex;
			flex-direction: column;
			align-items: center;
			justify-content: center;
			border-radius: 50%;
			font-size: 12px;
			padding: 0;
			background-color: #EEEEEE;
			width: 50px;
			height: 50px;
			line-height: 20px;
			
			.icon{
				font-size: 14px
			}
			
			&::after{
				border: none;
			}
			
			&:active{
				background-color: #EEEEEE;
			}
		}
	}
</style>
