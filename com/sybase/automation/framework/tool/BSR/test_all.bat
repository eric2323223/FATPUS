cd /d %~dp0


call autotest ClientOnlyMBO > ClientOnlyMBO.log
sleep 300


cd %BSR_HOME%
call autotest DBMBO_ASA > DBMBO_ASA.log
sleep 300

cd %BSR_HOME%
call autotest ObjectQuery > ObjectQuery.log
sleep 300

rem cd %BSR_HOME%
rem call autotest Remedy > Remedy.log
sleep 300

cd %BSR_HOME%
call autotest RESTWS > RESTWS.log
sleep 300

cd %BSR_HOME%
call autotest ResultFolder > ResultChecker.log
sleep 300

cd %BSR_HOME%
call autotest Synchronization > Synchronization.log 
sleep 300

cd %BSR_HOME%
call autotest ComplexType > ComplexType.log
sleep 300

cd %BSR_HOME%
call autotest CacheGroup > CacheGroup.log
sleep 300

cd %BSR_HOME%
call autotest CachePartition > CachePartition.log
sleep 300

cd %BSR_HOME%
call autotest OnlineCache > OnlineCache.log
sleep 300

cd %BSR_HOME%
call autotest WSEnhancement > WSEnhancement.log
sleep 300

cd %BSR_HOME%
call autotest CodeGen > CodeGen.log
sleep 300

cd %BSR_HOME%
call autotest Nullability > Nullability.log
sleep 300

cd %BSR_HOME%
call autotest Migration > Migration.log
sleep 300