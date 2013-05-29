package com.zqdy.action;
 
import java.io.File;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import com.zqdy.core.action.BaseAction;
import com.zqdy.core.common.dto.CommonDto;
import com.zqdy.core.utils.DownHtml;
import com.zqdy.dao.po.TActor;
import com.zqdy.dao.po.TMovieActor;
import com.zqdy.dao.po.TArea;
import com.zqdy.dao.po.TDirector;
import com.zqdy.dao.po.TGenre;
import com.zqdy.dao.po.TMovie;
import com.zqdy.dao.po.TMovieArea;
import com.zqdy.dao.po.TMovieDirector;
import com.zqdy.dao.po.TMovieGenre;
import com.zqdy.service.MovieService;
import com.zqdy.service.SnatchService;



public class MovieAction extends BaseAction {
	
	private MovieService movieService;
	
	private SnatchService snatchService;
	
	private String hotMovie;
	
	private String commMovie;
	
	private File movieImg;
	 
	private String movieName;
	
	private Integer recommMovieType;
	
	private String sourceServer;
	
	private TMovie movie;
	
	private String areas="";
	
	private String directors="";
	
	private String actors="";
	
	private String genres="";
	
	private List movieTypeList;

	private Integer[] embedSourceServer;
	
	private String[] embeds;
	
	private Integer[] embedIds;
	

	public String goMovieList(){
		init();
		StringBuffer hql = new StringBuffer(" where 1=1 ");
		if(movieName!=null){
			hql.append(" and name like '%"+movieName.trim()+"%' ");
		}
		if(sourceServer!=null&&!"0".equals(sourceServer)){
			hql.append(" and id in ( select movie.id from TEmbed where serverServer.id="+sourceServer+" )");
		}
		if("1".equals(hotMovie)){
			hql.append(" and isHot=1 ");
		}
		 
		if(recommMovieType!=null&&recommMovieType.intValue()!=0){
			hql.append(" and id in( select movieId from TRecoMovie where typeId="+recommMovieType+" ) ");
		}
		
		hql.append(" order by addTime desc ");
		CommonDto dto = (CommonDto) movieService.getResult(pageSize,
				cpage - 1, " TMovie ", hql.toString());
		resultList = (List) dto.getProperty("result");
		Integer pageCount = (Integer) dto.getProperty("pageCount");
		this.setTotal(pageCount);
		
		movieTypeList = movieService.getAllMovieType();
		
		List snatchServerList = snatchService.getAllSnatchServer();
		request.setAttribute("snatchServerList", snatchServerList);
		return SUCCESS;
	}
	
	public String goAddMovie(){
		init();
		List snatchServerList = snatchService.getAllSnatchServer();
		request.setAttribute("snatchServerList", snatchServerList);
		
		return SUCCESS;
	}
	public String doAddMovie(){
		init();
		String imagePath = null;
		if(movieImg!=null){
			imagePath = DownHtml.saveMovieImage(movieImg,"mv",null,movie.getName());
			movie.setImageUrl(imagePath);
		}
		movie.setAddTime(new Date());
		msg = movieService.saveMovie(movie, areas, directors, actors, genres, embedSourceServer, embeds, embedIds);
		outMsg("{msg:'"+msg+"'}");
		return SUCCESS;
	}
	public String goEditMovie(){
		init();
		String movieId = request.getParameter("movieId");
		movie = movieService.getMovivById(Integer.valueOf(movieId));
		if(movie!=null){
			List actorList = movie.getActorList();
			if(actorList!=null){
				 
				for(int i=0;i<actorList.size();i++){
					TMovieActor actorMovie = (TMovieActor)actorList.get(i);
					if(actorMovie!=null){
						TActor actor = actorMovie.getActor();
						if(actor!=null){
							actors+=actor.getName()+"/";
						}
					}
					
				}
			}
			List directorList = movie.getDirectorList();
			if(directorList!=null&&directorList.size()>0){
 
				for(int i=0;i<directorList.size();i++){
					TMovieDirector dir = (TMovieDirector)directorList.get(i);
					if(dir!=null&&dir.getDirector()!=null){
						directors+=dir.getDirector().getName()+"/";
					}
					
				}
			}
			List genList = movie.getGenreList();
			if(genList!=null&&genList.size()>0){
				 
				for(int i=0;i<genList.size();i++){
					TMovieGenre gen = (TMovieGenre)genList.get(i);
					if(gen!=null&&gen.getGenre()!=null){
						genres+=gen.getGenre().getName()+"/";
					}
					
				}
			}
			
			 
			List areaList = movie.getAreaList();
			if(areaList!=null&&areaList.size()>0){
				 
				for(int i=0;i<areaList.size();i++){
					TMovieArea area = (TMovieArea)areaList.get(i);
					if(area!=null&&area.getArea()!=null){
						areas+=area.getArea().getName()+"/";
					}
					
				}
			}
			
			
		}

		List snatchServerList = snatchService.getAllSnatchServer();
		request.setAttribute("snatchServerList", snatchServerList);
		
		return SUCCESS;
	}
	public String doEditMovie(){
		init();
		String imagePath = null;
		if(movieImg!=null){
			imagePath = DownHtml.saveMovieImage(movieImg,"mv",null,movie.getName());
			movie.setImageUrl(imagePath);
		}
		
		msg = movieService.saveMovie(movie, areas, directors, actors, genres, embedSourceServer, embeds, embedIds);
		outMsg("{msg:'"+msg+"'}");
		return null;
	}
	
	/**
	 * 推荐电影
	 * @return
	 */
	public String recommMovie(){
		init();
		String movieId = request.getParameter("movieId");
		String recommIds = request.getParameter("recommIds");
		String shortSummary = request.getParameter("shortSummary");
		if(movieId==null){
			msg = "更新失败，请选择需要操作的电影";
		}else{
			 
			if(recommIds!=null&&!"".equals(recommIds.trim())){
				msg = movieService.updateMovieRecomm(recommIds, Integer.valueOf(movieId),shortSummary);
			}
		}
		outMsg("{msg:'"+msg+"'}");
		return null;
	}
	
	
	/**
	 * 推荐电影
	 * @return
	 */
	public String deleteRecommMovie(){
		init();
		String movieIds = request.getParameter("movieIds");
		String typId = request.getParameter("typId");
		if(movieIds==null||typId==null){
			msg = "删除失败，请选择需要操作的电影或电影类型";
		}else{
			msg = movieService.deleteMovieRecomm(movieIds, Integer.valueOf(typId));
		}
		outMsg("{msg:'"+msg+"'}");
		return null;
	}
	 
	/**
	 * 最热门电影
	 * @return
	 */
	public String hotRecommMovie(){
		init();
		String hotIds = request.getParameter("hotIds");
		String recommIds = request.getParameter("recommIds");
		if(hotIds==null&&recommIds==null){
			msg = "更新失败，请选择需要操作的电影";
		}else{
			if(hotIds!=null&&!"".equals(hotIds.trim())){
				msg = movieService.updateMovieHot(hotIds, 1);
			}
			if(recommIds!=null&&!"".equals(recommIds.trim())){
				msg = movieService.updateMovieRecomm(recommIds, 1);
			}
		}
		outMsg("{msg:'"+msg+"'}");
		return null;
	}
	
	/**
	 *  取消 推荐、最热门电影
	 * @return
	 */
	public String unRecommMovie(){
		init();
		String mvIds = request.getParameter("mvIds");
		String typeId = request.getParameter("typeId");
		if(mvIds==null||typeId==null||Integer.valueOf(typeId)==0){
			msg = "更新失败，请选择需要操作的电影和电影类型";
		}else{	 
			msg = movieService.deleteMovieRecomm(mvIds, Integer.valueOf(typeId));
			 
		}
		outMsg("{msg:'"+msg+"'}");
		return null;
	}
	
	public String deleteEmbed(){
		init();
		String embedId = request.getParameter("embedId");
		msg = movieService.deleteEmbedId(embedId);
		outMsg(msg);
		return null;
	}

	public void setMovieService(MovieService movieService) {
		this.movieService = movieService;
	}

	public void setSnatchService(SnatchService snatchService) {
		this.snatchService = snatchService;
	}
	
	

	public TMovie getMovie() {
		return movie;
	}

	public void setMovie(TMovie movie) {
		this.movie = movie;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public String getSourceServer() {
		return sourceServer;
	}

	public void setSourceServer(String sourceServer) {
		this.sourceServer = sourceServer;
	}

	public String getActors() {
		if(actors!=null&&actors.endsWith("/")){
			actors = actors.substring(0, actors.length()-1);
		}
		return actors;
	}

	public void setActors(String actors) {
		this.actors = actors;
	}

	public String getAreas() {
		if(areas!=null&&areas.endsWith("/")){
			areas = areas.substring(0, areas.length()-1);
		}
		return areas;
	}

	public void setAreas(String areas) {
		this.areas = areas;
	}

	public String getDirectors() {
		if(directors!=null&&directors.endsWith("/")){
			directors = directors.substring(0, directors.length()-1);
		}
		return directors;
	}

	public void setDirectors(String directors) {
		this.directors = directors;
	}

	public String getGenres() {
		if(genres!=null&&genres.endsWith("/")){
			genres = genres.substring(0, genres.length()-1);
		}
		return genres;
	}

	public void setGenres(String genres) {
		this.genres = genres;
	}

	public Integer[] getEmbedIds() {
		return embedIds;
	}

	public void setEmbedIds(Integer[] embedIds) {
		this.embedIds = embedIds;
	}

	public String[] getEmbeds() {
		return embeds;
	}

	public void setEmbeds(String[] embeds) {
		this.embeds = embeds;
	}

	public Integer[] getEmbedSourceServer() {
		return embedSourceServer;
	}

	public void setEmbedSourceServer(Integer[] embedSourceServer) {
		this.embedSourceServer = embedSourceServer;
	}

	public File getMovieImg() {
		return movieImg;
	}

	public void setMovieImg(File movieImg) {
		this.movieImg = movieImg;
	}

	public String getHotMovie() {
		return hotMovie;
	}

	public void setHotMovie(String hotMovie) {
		this.hotMovie = hotMovie;
	}

	public String getCommMovie() {
		return commMovie;
	}

	public void setCommMovie(String commMovie) {
		this.commMovie = commMovie;
	}

	public List getMovieTypeList() {
		return movieTypeList;
	}

	public void setMovieTypeList(List movieTypeList) {
		this.movieTypeList = movieTypeList;
	}

	public Integer getRecommMovieType() {
		return recommMovieType;
	}

	public void setRecommMovieType(Integer recommMovieType) {
		this.recommMovieType = recommMovieType;
	}

	 
	
	
}
