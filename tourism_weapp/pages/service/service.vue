<template>
	<view>
		<image class="haibao" src="https://whlyj.taian.gov.cn/picture/-1/1902251556008257644.jpg" mode=""></image>

		<text class="h2 title">景区介绍</text>
	
		<view class="guide_container">
			<view @click="gotoPage('child','/home/children/introduce/introduce')">
				<text class="h2">景区概况</text>
				<text>服务游客 助理旅游</text>
			</view>
		</view>
		
		<text class="h2 title">游玩保障服务</text>
		
		<view class="card_container">
			<view @click="callPhone('123456')" class="item" style="background: linear-gradient(to bottom,#07BCDA,#00D7C7 );">
				<view>
					<text class="h3">旅游咨询</text>
					<text>12301</text>
				</view>
				<view class="cuIcon-phone"></view>
			</view>
			<view @click="callPhone('12315')" class="item" style="background: linear-gradient(to bottom,#9C6DF7,#D780F1 );">
				<view>
					<text class="h3">投诉求助</text>
					<text>投诉有效 实时高效</text>
				</view>
				<view class="cuIcon-edit"></view>
			</view>
		</view>
		
		<text class="h2 title">旅游公共服务</text>
		
		<view class="card_container">
			<view @click="toTraffic" class="item" style="background: linear-gradient(to bottom,#FA8733,#FAA824);">
				<view>
					<text class="h3">公交信息</text>
					<text>公交线路查询</text>
				</view>
				<view class="cuIcon-deliver"></view>
			</view>
			<view class="item" style="background: linear-gradient(to bottom,#2FD162,#7ADF65);" @click="gotoWeather">
				<view>
					<text class="h3">一周天气</text>
					<text>一周天气 尽在掌握</text>
				</view>
				<view class=".cuIcon-favorfill"></view>
			</view>
		</view>
		
	</view>
</template>

<script>
	export default {
		data() {
			return {
				
			}
		},
		methods: {
			gotoPage(type,path){
				if (type==='tabbar') {
					uni.switchTab({
						url:'/pages' + path
					})
				} else{
					uni.navigateTo({
						url:'/pages' + path
					})
				}
			},
			gotoWeather(){
				uni.navigateTo({
					url:'/pages/service/children/weather/weather'
				})
			},
			callPhone(phone){
				uni.makePhoneCall({
					phoneNumber: phone
				})
			},
			toTraffic(){
				uni.navigateTo({
					url:'/pages/service/children/traffic/traffic'
				})
			}
		}
	}
</script>

<style lang="scss">
	page{
		padding: 14px;
	}
	
	.guide_container{
		margin-top: 10px;
		color: #fff;
		background-color: #f0f0f0; /* 网格项背景色，可以根据需要调整 */
		padding: 10px;
		border-radius: 6px;
		flex: 1;
		background: linear-gradient(to right,#2996FD,#44BAFA);
		
		.h3{
			font-size: 17px;
		}
	
	}
	
	.haibao{
		width: 100%;
		height: 120px;
		border-radius: 10px;
	}
	
	.title{
		margin: 14px 0;
	}
	
	.card_container{
		display: grid;
		grid-template-columns: 1fr 1fr;
		grid-gap: 16px;
		
		.item{
			border-radius: 4px;
			padding: 10px;
			color: #FFF;
			display: grid;
			grid-template-columns: 1fr 40px;
			align-items: center;
			
			view:nth-child(2){
				background-color: rgba(255, 255, 255, 0.2);
				height: 40px;
				border-radius: 50%;
				text-align: center;
				line-height: 40px;
				font-size: 20px;
			}
		}
	}

</style>
