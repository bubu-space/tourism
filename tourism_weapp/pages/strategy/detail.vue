<template>
	<view>
		<!-- 轮播图 -->
		<swiper class="screen-swiper" :indicator-dots="true"
			:circular="true" :autoplay="true" interval="3000" duration="500">
			<swiper-item v-for="(item,index) in strategy.images" :key="index">
				<image :src="item.url" mode="aspectFill"></image>
			</swiper-item>
		</swiper>
		
		<!-- 发布信息 -->
		<view class="padding-sm text-df bg-white" style="padding-bottom: 100px;">
			<view class="title text-xl">{{strategy.title}}</view>
			<view class="margin-top-xs text-grey">{{strategy.describle}}</view>
			<view class="margin-top-xl text-grey">{{strategy.createTime}}</view>
		</view>
		
		  <!-- 底部操作条：背景颜色、对应图标、字体颜色，可自定义 -->
			<view class="cu-bar tabbar bg-white foot">
				<view class="flex align-center  flex-sub justify-center">
					<text v-if="!isLike" @click="handleStar" class="cuIcon-like margin-left-sm" style="color: #c6c6c6;font-size: 20px;"></text>
					<text v-else  @click="handleStar" class="cuIcon-likefill margin-left-sm" style="color: red;font-size: 20px;"></text>
					<text class="margin-left-xs text-df">{{strategy.likeCount}}</text>
				</view>
				<view class="flex align-center flex-sub justify-center">
					<text class="cuIcon-attention margin-right-sm"  style="color: #c6c6c6;font-size: 20px;"></text>
					<text>{{strategy.watchCount}}</text>
				</view>
			</view>
		
	</view>
</template>

<script>
	import strategyApi from "@/api/strategyApi.js"
	
	export default {
		data() {
			return {
				strategy: {},
				isLike: false
			}
		},
		onLoad(option) {
			let param = {id: option.id}
			strategyApi.detail(param).then(res => {
				this.strategy = res.data
				strategyApi.isuserlike({strategyid: this.strategy.id}).then(res => {
					this.isLike = res.data
				})
			})
			
		},
		methods: {
			handleStar(){
				strategyApi.like({id: this.strategy.id}).then(res => {
					if(this.isLike){
						this.strategy.likeCount-=1
					}else{
						this.strategy.likeCount+=1
					}
					this.isLike = !this.isLike
				})
			}
		}
	}
</script>

<style lang="scss">
	.screen-swiper{
		height: 80vh;
	}
	
	.cu-bar.foot{
		box-shadow: none;
	}
</style>
